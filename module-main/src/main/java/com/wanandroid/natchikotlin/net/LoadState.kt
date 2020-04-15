package com.wanandroid.natchikotlin.net

/**
 * Created by JustinWjq
 * @date 2019-12-23.
 * description：
 */
sealed class LoadState<out T> {
//    companion object{
//        fun <T> onAppSuccess(data: T): LoadState<T> = Success(data)
//        fun <T> onAppLoading(loadingMessage:String): LoadState<T> = Loading(loadingMessage)
//        fun <T> onAppError(error: String): LoadState<T> = Error(error)
//    }


    class Loading(val loadingMessage:String) : LoadState<Nothing>()
    class Success<out T>(val data: T) : LoadState<T>()
    class Error(val error: String) : LoadState<Nothing>()
}