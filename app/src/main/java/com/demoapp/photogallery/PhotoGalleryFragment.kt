package com.demoapp.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.demoapp.photogallery.api.FlickrFetcher
import com.demoapp.photogallery.databinding.FragmentPhotoGalleryBinding

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment : Fragment() {

    private lateinit var binding: FragmentPhotoGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flickrLiveData: LiveData<String> = FlickrFetcher().fetchPhotos()
        flickrLiveData.observe(this, Observer { responseString ->
            Log.d(TAG, "Response received: $responseString")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoGalleryBinding.inflate(
            inflater,
            container,
            false
        )

        binding.photoRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return binding.root
    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}