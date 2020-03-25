package com.wanandroid.natchikotlin.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wanandroid.natchikotlin.data.bean.ApiResponse
import com.wanandroid.natchikotlin.ui.PageBean

/**
 * Created by JustinWjq
 * @date 2019-11-06.
 * description：
 */
open class BasePageViewModel :ViewModel() {
    val refreshTrigger = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    public val page = MutableLiveData<Int>()
    val refreshing = MutableLiveData<Boolean>()
    val moreLoading = MutableLiveData<Boolean>()
    val hasMore = MutableLiveData<Boolean>()
    val autoRefresh = MutableLiveData<Boolean>()//SmartRefreshLayout自动刷新标记



    fun loadMore() {
        page.value = (page.value ?: 0) + 1
        moreLoading.value = true

    }

    fun initPage(){
        page.value = 0
    }

    /**
     * 自动刷新
     */
    fun autoRefresh() {
        autoRefresh.value = true
    }

    open fun refresh() {
        page.value = (page.value ?: 0) + 1
        refreshing.value = true
    }

    open fun loadData() {
        refreshTrigger.value = true
        loading.value = true
    }

    /**
     * 处理分页数据
     * 处理源数据，通过map
     */
    fun <T> mapPage(source: LiveData<ApiResponse<PageBean<T>>>): LiveData<PageBean<T>> {
        return Transformations.map(source) {
            refreshing.value = false
            moreLoading.value = false
            hasMore.value = !(it?.data?.over ?: false)
            it.data ?: PageBean(1, ArrayList(), 0, true, 1, 20, 0)
        }
    }




}