package com.wanandroid.natchikotlin.ui.square

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.wanandroid.natchikotlin.base.BasePageViewModel
import com.wanandroid.natchikotlin.data.bean.*
import com.wanandroid.natchikotlin.data.remotedatasource.NetWorkManger
import com.wanandroid.natchikotlin.data.remotedatasource.callApi
import com.wanandroid.natchikotlin.ui.hot.HotPageViewModel
import com.wanandroid.natchikotlin.utils.InjectorUtils

/**
 * Created by JustinWjq
 * @date 2019-10-16.
 * description：管理数据 将视图(Fragment,Activity)的数据和逻辑剥离出来，直到关联的视图销毁，viewhodel才消失，
 * 所以即使在旋转屏幕的时候，Fragment被重新创建的事件中，视图数据依旧会被保留
 */
class SquareViewModel : BasePageViewModel() {


    val pageItemList = MutableLiveData<PageBean<PageItemBean>>()



    fun getSerachWord(pageId:Int) {
        callApi(
            onStart = null,
            onRequest = {
                NetWorkManger.getInstance().getSquare(pageId)
            },
            onError = { code, message ->

                Log.i("message", message)
            },
            onSuccess = {

                pageItemList.value = it.data

            },
            onComplete = {}
        )
    }


}