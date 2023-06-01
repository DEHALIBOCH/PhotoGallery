package com.demoapp.photogallery.worker

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.demoapp.photogallery.GalleryItem
import com.demoapp.photogallery.MainActivity
import com.demoapp.photogallery.NOTIFICATION_CHANEL_ID
import com.demoapp.photogallery.QueryPreferences
import com.demoapp.photogallery.R
import com.demoapp.photogallery.api.FlickrFetcher

private const val TAG = "PollWorker"

class PollWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val query = QueryPreferences.getStoredQuery(context)
        val lastResultId = QueryPreferences.getLastResultId(context)
        val items: List<GalleryItem> = if (query.isEmpty()) {
            FlickrFetcher().fetchPhotosRequest()
                .execute()
                .body()
                ?.photos
                ?.galleryItems
        } else {
            FlickrFetcher().searchPhotosRequest(query)
                .execute()
                .body()
                ?.photos
                ?.galleryItems
        } ?: emptyList()

        if (items.isEmpty()) {
            return Result.success()
        }

        val resultId = items.first().id
        if (resultId == lastResultId) {
            Log.i(TAG, "Got an old result: $resultId")
        } else {
            Log.i(TAG, "Got a new result: $resultId")
            QueryPreferences.setLastResultId(context, resultId)

            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                MainActivity.newIntent(context),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val resources = context.resources
            val notification = NotificationCompat
                .Builder(context, NOTIFICATION_CHANEL_ID)
                .setTicker(resources.getString(R.string.new_pictures_title))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(resources.getString(R.string.new_pictures_text))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            val notificationManager = NotificationManagerCompat.from(context)

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notificationManager.notify(0, notification)
            }
            context.sendBroadcast(Intent(ACTION_SHOW_NOTIFICATION), PERM_PRIVATE)
        }

        return Result.success()
    }

    companion object {
        const val ACTION_SHOW_NOTIFICATION = "com.demoapp.photogallery.SHOW_NOTIFICATION"
        const val PERM_PRIVATE = "com.demoapp.photogallery.PRIVATE"
    }

}