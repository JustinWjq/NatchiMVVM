package com.wanandroid.natchikotlin.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.wanandroid.natchikotlin.data.LoadState
import com.wanandroid.natchikotlin.data.bean.ApiResponse
import com.wanandroid.natchikotlin.data.bean.PageBean
import com.wanandroid.natchikotlin.data.bean.PageItemBean
import com.wanandroid.natchikotlin.data.remotedatasource.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    val imageData = MutableLiveData<PageBean<PageItemBean>>()
    val loadState = MutableLiveData<LoadState<PageBean<PageItemBean>>>()
    fun getData() {
        launch(
           onStart =  {
                loadState.value = LoadState.Loading("")
            },
            onRequest = {
               val data1 = async {
                   ServiceBuilder.apiService.getSquare1(1).disposeData() }
                data1.await()

            },
            onSuccess ={
                imageData.value = it
                loadState.value = LoadState.Success(it!!)
            },
            onError = {
                loadState.value = LoadState.Error("${it.message}")
            },
            onComplete = {


            }
        )
    }



}



