package com.demoapp.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demoapp.photogallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, PhotoGalleryFragment.newInstance())
                .commit()
        }


    }
}
