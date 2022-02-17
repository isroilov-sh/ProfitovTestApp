package com.example.testapp.api

import androidx.lifecycle.LiveData
import com.example.testapp.data.ResponseData

interface Repository {
    fun getIds(): LiveData<List<Int>>
    fun getContent(id: Int): LiveData<ResponseData>
}