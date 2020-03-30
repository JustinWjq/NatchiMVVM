package com.wanandroid.natchikotlin.data.remotedatasource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wanandroid.natchikotlin.data.ResultUtils
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.lang.NullPointerException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


/**
 * Created by JustinWjq
 * @date 2019-10-12.
 * description：
 */
//将回调，转换为协程中的挂起函数
//调用 下面的 await 扩展函数，会挂起当前的协程
//resume 会恢复之前的协程
//resumeWithException，会恢复协程，但是抛出Exception
/**
 * Suspend extension that allows suspend [Call] inside of a coroutine.
 *
 * @return Result of request or throw exception
 */
 suspend fun <T : Any> Call<T>.await(): T {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T?>) {
                continuation.resumeWith(runCatching {
                    if (response.isSuccessful) {
                        response.body()
                            ?: throw NullPointerException("Response body is null: $response")
                    } else {
                        throw HttpException(response)
                    }
                })
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                continuation.resumeWithException(t)
            }
        })

        registerOnCompletion(continuation)
    }
}



/**
 * Suspend extension that allows suspend [Call] inside coroutine.
 *
 * @return Response for request or throw exception
 */
public suspend fun <T : Any?> Call<T>.awaitResponse(): Response<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resume(response)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                continuation.resumeWithException(t)
            }
        })

        registerOnCompletion(continuation)
    }
}

/**
 * Suspend extension that allows suspend [Call] inside coroutine.
 *
 * @return sealed class [Result] object that can be
 *         casted to [Result.Ok] (success) or [Result.Error] (HTTP error)
 *         and [Result.Exception] (other errors)
 */
public suspend fun <T : Any> Call<T>.awaitResult(): ResultUtils<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resumeWith(runCatching {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            ResultUtils.Exception(NullPointerException("Response body is null"))
                        } else {
                            ResultUtils.Ok(body, response.raw())
                        }
                    } else {
                        ResultUtils.Error(HttpException(response), response.raw())
                    }
                })
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                continuation.resume(ResultUtils.Exception(t))
            }
        })

        registerOnCompletion(continuation)
    }
}

private fun Call<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        try {
            cancel()
        } catch (ex: Throwable) {
            //Ignore cancel exception
        }
    }
}

fun <T> ViewModel.launch(
    onStart: (() -> Unit)? = null,
    onRequest: suspend CoroutineScope.() -> T,
    onSuccess: (T) -> Unit,
    onError: (e: Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
) {
    viewModelScope.launch(CoroutineExceptionHandler { _, e -> onError(e) }) {
        try {
            onStart?.invoke()
            val request = onRequest.invoke(this)
            onSuccess.invoke(request)
        } finally {
            onComplete()
        }
    }

}
