package com.example.moviestestapp.api

import com.example.moviestestapp.BuildConfig
import com.example.moviestestapp.data.ResponseData
import com.example.moviestestapp.utils.URLConstants
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaApi {
    @GET(URLConstants.MEDIA_URL)
    suspend fun getMedia(
        @Query("api-key") apiToken: String = BuildConfig.API_KEY
    ): ResponseData
}