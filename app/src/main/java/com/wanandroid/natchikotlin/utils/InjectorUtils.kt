package com.wanandroid.natchikotlin.utils

import android.content.Context
import com.wanandroid.natchikotlin.ui.home.viewmodel.HomeViewModelFactory
import com.wanandroid.natchikotlin.ui.hot.HotViewModelFactory
import com.wanandroid.natchikotlin.ui.knowledgesystem.KnowSystemViewModelFactory
import com.wanandroid.natchikotlin.ui.square.SquareViewModelFactory

/**
 * Created by JustinWjq
 * @date 2019-10-16.
 * description：
 */
object InjectorUtils {

    fun provideHomwListViewModelFactory(context: Context): HomeViewModelFactory {

        return HomeViewModelFactory()
    }

    fun provideKnowSystemViewModelFactory(context: Context): KnowSystemViewModelFactory {

        return KnowSystemViewModelFactory()
    }

    fun provideHotViewModelFactory(context: Context): HotViewModelFactory {

        return HotViewModelFactory()
    }


    fun provideSquareViewModelFactory(context: Context): SquareViewModelFactory {

        return SquareViewModelFactory()
    }
}