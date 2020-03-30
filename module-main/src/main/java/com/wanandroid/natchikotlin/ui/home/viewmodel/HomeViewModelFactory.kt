package com.wanandroid.natchikotlin.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by JustinWjq
 * @date 2019-10-16.
 * description：
 */
class HomeViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>)
            = HomePageViewModel() as T

}