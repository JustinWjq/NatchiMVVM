package com.wanandroid.natchikotlin.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.wanandroid.commonlib.utils.LogUtil
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.base.BasePageViewModel
import com.wanandroid.natchikotlin.data.LoadState
import com.wanandroid.natchikotlin.data.bean.PageItemBean
import com.wanandroid.natchikotlin.data.bean.BannerBean
import com.wanandroid.natchikotlin.data.bean.HomeTabBean
import com.wanandroid.natchikotlin.data.bean.PageBean
import com.wanandroid.natchikotlin.data.remotedatasource.*
import kotlinx.coroutines.async

/**
 * Created by JustinWjq
 * @date 2019-10-16.
 * description：管理数据 将视图(Fragment,Activity)的数据和逻辑剥离出来，直到关联的视图销毁，viewhodel才消失，
 * 所以即使在旋转屏幕的时候，Fragment被重新创建的事件中，视图数据依旧会被保留
 * ------>>>>usercase
 */
class HomePageViewModel : BasePageViewModel() {
    //todo MutableLiveData最好用来定义变化的基本类型 数据发生变化，会通知给订阅者

    val banners = MutableLiveData<List<BannerBean>>()

    val isLoad = MutableLiveData<Boolean>()

    var homeTabLists = MutableLiveData<List<HomeTabBean>>()

    //todo 关于每个页面的数据，我觉得还是放到viewModel进行管理，这样既能粘性，又能为六大原则

    //todo 加载列表的index

    // <!--项目 热门 动态 体系-->
    init {
        isLoad.value = false
        homeTabLists.value = arrayListOf(
            HomeTabBean().apply {
                iconCode = 1
                iconTab = R.drawable.ic_top
                tabContent = "项目"
            },
            HomeTabBean().apply {
                iconCode = 1
                iconTab = R.drawable.ic_top
                tabContent = "热门"
            },
            HomeTabBean().apply {
                iconCode = 1
                iconTab = R.drawable.ic_top
                tabContent = "动态"
            },
            HomeTabBean().apply {
                iconCode = 1
                iconTab = R.drawable.ic_top
                tabContent = "体系"
            }
        )


    }

    var pageList = MutableLiveData<LoadState<MutableList<PageItemBean>>>()

    fun getListData(pageIndex : Int) {
        callApi(
            onStart = null,
            onRequest = {
                NetWorkManger.getInstance().getProjectList(pageIndex)
            },
            onError = { code, message ->

                Log.i("message", message)
            },
            onSuccess = {

//                pageList.value = it

            },
            onComplete = {

            }
        )
    }


    fun getBannerList() {
        callApi(
            onStart = null,
            onRequest = {
                NetWorkManger.getInstance().getHomeBanner()
            },
            onError = { code, message ->

            },
            onSuccess = {
                banners.value = it.data
            },
            onComplete = {}
        )


    }
    fun getTopList( index : Int){
        launch(
            onStart =  {
                pageList.value = LoadState.Loading("正在加载中...")
            },
            onRequest = {
                val data1 = async {
                    ServiceBuilder.apiService.getTop().disposeData()
                }

                val listData1 =data1.await()
                val data2 =  async {
                    ServiceBuilder.apiService.getProjectList(index, "402").disposeData()
                }
               val listData = data2.await()?.datas
                listData1?.addAll(listData!!)
                listData1
            },
            onSuccess ={
                LogUtil.d("wananzhuo",it.toString())
                pageList.value = LoadState.Success(it!!)
            },
            onError = {
                LogUtil.d("wananzhuo",it.message!!)
                pageList.value = LoadState.Error(it.message!!)
            },
            onComplete = {


            }
        )
    }



}