package com.example.moviestestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviestestapp.api.MainRepository
import com.example.moviestestapp.data.ResponseData
import com.example.moviestestapp.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MediaViewModel(private val mainRepository: MainRepository) : ViewModel(), ExceptionHandler {

    init {
        with(mainRepository) {
            setCoroutineScope(CoroutineScope(Dispatchers.IO))
            setExceptionHandler(this@MediaViewModel)
        }
    }

    fun getMediaFromRepository(): LiveData<ResponseData> = mainRepository.getMedia()

    override fun onException(throwable: Throwable) {}
}