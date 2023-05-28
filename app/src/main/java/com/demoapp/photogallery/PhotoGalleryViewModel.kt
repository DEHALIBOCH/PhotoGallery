package com.demoapp.photogallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.demoapp.photogallery.api.FlickrFetcher

class PhotoGalleryViewModel(private val app: Application) : AndroidViewModel(app) {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetcher = FlickrFetcher()
    private val mutableSearchTerm = MutableLiveData<String>()

    val searchTerm: String
        get() = mutableSearchTerm.value ?: ""

    init {
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)

        galleryItemLiveData = Transformations.switchMap(mutableSearchTerm) {
            if (it.isBlank()) {
                flickrFetcher.fetchPhotos()
            } else {
                flickrFetcher.searchPhotos(it)
            }
        }
    }

    fun fetchPhotos(query: String) {
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }

}