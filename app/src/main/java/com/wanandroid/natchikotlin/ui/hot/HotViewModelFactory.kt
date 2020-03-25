package com.wanandroid.natchikotlin.ui.hot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by JustinWjq
 * @date 2019-10-16.
 * description：
 */
class HotViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>)
            = HotPageViewModel() as T

}