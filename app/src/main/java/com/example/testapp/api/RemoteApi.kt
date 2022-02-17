package com.example.testapp.api

import com.example.testapp.data.ResponseData
import com.example.testapp.utils.URLConstants
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApi {
    @GET(URLConstants.IDS_URL)
    suspend fun getIds(): List<Int>

    @GET(URLConstants.CONTENT_URL)
    suspend fun getContent(@Path("id") id: Int): ResponseData
}