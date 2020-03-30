package com.wanandroid.natchikotlin.ui.hot

import android.util.Log
import androidx.lifecycle.*
import com.wanandroid.natchikotlin.base.BasePageViewModel
import com.wanandroid.natchikotlin.data.bean.*
import com.wanandroid.natchikotlin.data.remotedatasource.NetWorkManger
import com.wanandroid.natchikotlin.data.remotedatasource.callApi

/**
 * Created by JustinWjq
 * @date 2019-10-16.
 * description：管理数据 将视图(Fragment,Activity)的数据和逻辑剥离出来，直到关联的视图销毁，viewhodel才消失，
 * 所以即使在旋转屏幕的时候，Fragment被重新创建的事件中，视图数据依旧会被保留
 */
class HotPageViewModel : BasePageViewModel() {

    val hotKeyList = MutableLiveData<List<HotKeyBean>>()

    fun getHotKey() {
        callApi(
            onStart = null,
            onRequest = {
                NetWorkManger.getInstance().getHotKey()
            },
            onError = { code, message ->

                Log.i("message", message)
            },
            onSuccess = {

                hotKeyList.value = it.data

            },
            onComplete = {}
        )
    }

    val serachWordList = MutableLiveData<PageBean<PageItemBean>>()

    fun getSerachWord(keyWord:String) {
        callApi(
            onStart = null,
            onRequest = {
                NetWorkManger.getInstance().getQuery(0,keyWord)
            },
            onError = { code, message ->

                Log.i("message", message)
            },
            onSuccess = {

                serachWordList.value = it.data

            },
            onComplete = {}
        )
    }


}