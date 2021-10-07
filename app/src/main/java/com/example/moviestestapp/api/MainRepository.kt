package com.example.moviestestapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviestestapp.data.ResponseData
import com.example.moviestestapp.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainRepository(private val mediaService: MediaApi) : Repository {

    private var handler: ExceptionHandler? = null
    private var scope: CoroutineScope? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handler?.onException(throwable)
    }

    fun setExceptionHandler(exceptionHandler: ExceptionHandler) {
        handler = exceptionHandler
    }

    fun setCoroutineScope(scope: CoroutineScope) {
        this.scope = scope
    }

    override fun getMedia(): LiveData<ResponseData> {
        val liveData = MutableLiveData<ResponseData>()
        scope?.launch(exceptionHandler) {
            liveData.postValue(mediaService.getMedia())
        }
        return liveData
    }
}