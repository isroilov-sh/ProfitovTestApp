package com.example.testapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapp.data.ResponseData
import com.example.testapp.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainRepository(private val remoteService: RemoteApi) : Repository {

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

    override fun getIds(): LiveData<List<Int>> {
        val liveData = MutableLiveData<List<Int>>()
        scope?.launch(exceptionHandler) {
            liveData.postValue(remoteService.getIds())
        }
        return liveData
    }

    override fun getContent(id: Int): LiveData<ResponseData> {
        val liveData = MutableLiveData<ResponseData>()
        scope?.launch(exceptionHandler) {
            liveData.postValue(remoteService.getContent(id))
        }
        return liveData
    }

}