package com.wanandroid.natchikotlin.data.remotedatasource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.EOFException
import java.lang.NullPointerException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by JustinWjq
 * @date 2019-10-18.
 * description：
 */


/**
 *
 *
 *将回调，转换为协程中的挂起函数
 *调用 下面的 await 扩展函数，会挂起当前的协程
 *resume 会恢复之前的协程
 *resumeWithException，会恢复协程，但是抛出Exception
 */
//public suspend fun <T> Call<T>.aWait(): T {
//
//
//    //suspendCoroutine 这个方法并不是帮我们启动协程的，它运行在协程当中并且帮我们获取到当前协程的 Continuation 实例，
//    // 也就是拿到回调，方便后面我们调用它的 resume 或者 resumeWithException 来返回结果或者抛出异常。
//    return suspendCoroutine { continuation ->
//        //获取当前协程实例
//        enqueue(object : Callback<T> {
//            override fun onFailure(call: Call<T>, t: Throwable) {
////                if (continuation.isCancelled) return
//                continuation.resumeWithException(t)
//            }
//
//            override fun onResponse(call: Call<T>, response: Response<T>) {
//                val body = response.body()
//
//                if (body != null) {
//                    continuation.resume(body)
//                } else {
//                    continuation.resumeWithException(NullPointerException("Response Body is Null : $response"))
//                }
//
//
//            }
//        })


//        continuation.invokeOnCancellation {
//            try {
//                cancel()
//            } catch (ex: Throwable) {
//
//            }
//
//    }
//
//}

//
//    suspend fun  await1(): String=suspendCancellableCoroutine { continuation ->
//        //        //获取当前协程实例
//        ServiceBuilder.weatherService.getWeathers().enqueue(object : Callback<String> {
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                if (continuation.isCancelled) return
//                continuation.resumeWithException(t)
//            }
//
//            override fun onResponse(call: Call<String>, response: Response<String>?) {
//                continuation.resumeWith(runCatching {
//                    if (response!!.isSuccessful) {
//                        response.body() ?: throw NullPointerException("Response Body is Null : $response")
//                    } else {
//                        throw HttpException(response)
//                    }
//
//                })
//
//
//            }
//        })
//
//
//        continuation.invokeOnCancellation {
//            try {
//                cancel()
//            } catch (ex: Throwable) {
//
//            }
//
//        }
//
//    }

/**
 *
 *
 */
fun <T> ViewModel.callApi(
    onStart: (() -> Unit)? = null,
    onRequest: suspend () -> T,
    onSuccess: suspend (T) -> Unit,
    onError: suspend (Int, String) -> Unit,
    onComplete: (() -> Unit)? = null
) {

    viewModelScope.launch {

        withContext(Dispatchers.Main) { onStart?.invoke() }
        try {
            val result = withContext(Dispatchers.IO) { return@withContext onRequest.invoke() }
            withContext(Dispatchers.Main) {return@withContext onSuccess.invoke(result) }
        } catch (e: Exception) {
            var code = StatusCode.NET_CODE_ERROR
            val message = when (e) {
                is SocketTimeoutException -> StatusCode.SOCKET_TIMEOUT_EXCEPTION
                is ConnectException -> StatusCode.CONNECT_EXCEPTION
                is UnknownHostException -> StatusCode.UNKNOWN_HOST_EXCEPTION
                is EOFException -> StatusCode.EMPTY_RESPONSE_EXCEPTION
                is JsonSyntaxException -> StatusCode.JSON_SYNTAX_EXCEPTION
                is TimeoutException -> StatusCode.TIMEOUT_EXCEPTION
                is HttpException -> {
                    code = e.code()
                    e.message()
                }
                else -> e.message
            }
            withContext(Dispatchers.Main) { onError.invoke(code, message!!) }
        } finally {
            //延时1秒结束,避免loading动画执行不完整
            kotlinx.coroutines.delay(1000)
            withContext(Dispatchers.Main) { onComplete?.invoke() }
        }

    }
}



