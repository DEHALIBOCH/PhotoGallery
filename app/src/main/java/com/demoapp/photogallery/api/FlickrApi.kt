package com.demoapp.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

    /**
     * By default, Flickr gives a response in XML format.
     *
     * To get a response in JSON format, we need to specify: format=json
     *
     * Setting the nojsoncallback value to 1 (nojsoncallback=1) instructs Flickr to remove
     * parentheses from the response. This makes it easier for Kotlin to parse the answer.
     *
     * Specifying the extras parameter with the url_s value (extras=url_s) instructs
     * Flickr to add the URL of the mini version of the image, if there is one.
     *
     */

    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=${ApiKey.API_KEY}" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    fun fetchPhotos(): Call<String>

}