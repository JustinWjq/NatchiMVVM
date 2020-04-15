package com.wanandroid.natchikotlin.ui.wenda

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wanandroid.commonlib.utils.LogUtils
import com.wanandroid.natchikotlin.base.BasePageViewModel
import com.wanandroid.natchikotlin.net.*
import com.wanandroid.natchikotlin.net.bean.PageItemBean
import kotlinx.coroutines.async

class WendaViewModel : BasePageViewModel() {

    var pageItemList = MutableLiveData<LoadState<MutableList<PageItemBean>>>()

    fun getTopList( index : Int){
        launch(
            onStart =  {
                pageItemList.value = LoadState.Loading("正在加载中...")
            },
            onRequest = {

                ServiceBuilder.apiService.getWenda(1).disposeData()


            },
            onSuccess ={
                LogUtils.d("wananzhuo",it.toString())

                pageItemList.value = LoadState.Success(it?.datas!!)
            },
            onError = {
                LogUtils.d("wananzhuo",it.message!!)
                pageItemList.value = LoadState.Error(it.message!!)
            },
            onComplete = {


            }
        )
    }

}
