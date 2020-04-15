package com.wanandroid.natchikotlin.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wanandroid.natchikotlin.net.*
import com.wanandroid.natchikotlin.net.bean.PageItemBean
import com.wanandroid.net.data.bean.PageBean
import kotlinx.coroutines.async

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



