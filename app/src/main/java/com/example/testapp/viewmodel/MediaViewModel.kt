package com.example.testapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.api.MainRepository
import com.example.testapp.data.ResponseData
import com.example.testapp.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MediaViewModel(private val mainRepository: MainRepository) : ViewModel(), ExceptionHandler {

    init {
        with(mainRepository) {
            setCoroutineScope(CoroutineScope(Dispatchers.IO))
            setExceptionHandler(this@MediaViewModel)
        }
    }

    fun getIds(): LiveData<List<Int>> = mainRepository.getIds()

    fun getContext(id: Int): LiveData<ResponseData> = mainRepository.getContent(id)

    override fun onException(throwable: Throwable) {
        Log.e("Error!", "onException: $throwable")
    }
}