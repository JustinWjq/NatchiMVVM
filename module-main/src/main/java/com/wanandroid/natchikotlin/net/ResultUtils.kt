package com.wanandroid.natchikotlin.net

import okhttp3.Response
import retrofit2.HttpException

/**
 * Created by JustinWjq
 * @date 2019-10-12.
 * description：
 */
/**
 * Sealed class of HTTP result
 */
@Suppress("unused")
public sealed class ResultUtils<out T : Any> {
    /**
     * Successful result of request without errors
     */
    public class Ok<out T : Any>(
        public val value: T,
        override val response: Response
    ) : ResultUtils<T>(),
        ResponseResult {
        override fun toString() = "Result.Ok{value=$value, response=$response}"
    }

    /**
     * HTTP error
     */
    public class Error(
        override val exception: HttpException,
        override val response: Response
    ) : ResultUtils<Nothing>(),
        ErrorResult,
        ResponseResult {
        override fun toString() = "Result.Error{exception=$exception}"
    }

    /**
     * Network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response
     */
    public class Exception(
        override val exception: Throwable
    ) : ResultUtils<Nothing>(),
        ErrorResult {
        override fun toString() = "ResultUtils.Exception{$exception}"
    }

}

/**
 * Interface for [Result] classes with [okhttp3.Response]: [Result.Ok] and [Result.Error]
 */
public interface ResponseResult {
    val response: Response
}

/**
 * Interface for [Result] classes that contains [Throwable]: [Result.Error] and [Result.Exception]
 */
public interface ErrorResult {
    val exception: Throwable
}

/**
 * Returns [Result.Ok.value] or `null`
 */
public fun <T : Any> ResultUtils<T>.getOrNull(): T? = (this as? ResultUtils.Ok)?.value

/**
 * Returns [Result.Ok.value] or [default]
 */
public fun <T : Any> Result<T>.getOrDefault(default: T): T = getOrNull() ?: default

/**
 * Returns [Result.Ok.value] or throw [throwable] or [ErrorResult.exception]
 */
public fun <T : Any> ResultUtils<T>.getOrThrow(throwable: Throwable? = null): T {
    return when (this) {
        is ResultUtils.Ok -> value
        is ResultUtils.Error -> throw throwable ?: exception
        is ResultUtils.Exception -> throw throwable ?: exception
    }
}
