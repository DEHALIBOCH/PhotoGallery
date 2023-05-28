package com.demoapp.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demoapp.photogallery.api.FlickrFetcher

class PhotoGalleryViewModel: ViewModel() {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    init {
        galleryItemLiveData = FlickrFetcher().fetchPhotos()
    }

}