package com.wanandroid.natchikotlin.ui.square

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Created by JustinWjq
 * @date 2019-10-16.
 * description：
 */
class SquareViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>)
            = SquareViewModel() as T

}