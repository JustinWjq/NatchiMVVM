package com.wanandroid.natchikotlin.ui.knowledgesystem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wanandroid.net.data.bean.NavItemBean
import com.wanandroid.natchikotlin.net.NetWorkManger
import com.wanandroid.natchikotlin.net.callApi

class KnowSystemViewModel : ViewModel() {

    var pageList = MutableLiveData<List<NavItemBean>>()

    fun getNaviItemList() {
        callApi(
            onStart = null,
            onRequest = {
                NetWorkManger.getInstance().getNaviJson()
            },
            onError = { code, message ->

            },
            onSuccess = {
                pageList.value = it.data
            },
            onComplete = {}
        )


    }

}
