package com.example.moviestestapp.api

import androidx.lifecycle.LiveData
import com.example.moviestestapp.data.ResponseData

interface Repository {
    fun getMedia(): LiveData<ResponseData>
}