package com.demoapp.photogallery

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demoapp.photogallery.databinding.ActivityPhotoPageBinding

class PhotoPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager

        if (savedInstanceState == null) {
            val fragment = PhotoPageFragment.newInstance(intent.data)
            fragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, fragment)
                .commit()
        }
    }

    companion object {
        fun newIntent(context: Context, photoPageUri: Uri): Intent {
            return Intent(context, PhotoPageActivity::class.java).apply {
                data = photoPageUri
            }
        }
    }
}