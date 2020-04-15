package com.wanandroid.natchikotlin.net

import androidx.lifecycle.LiveData
import com.wanandroid.net.data.bean.ApiResponse
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.IllegalArgumentException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by JustinWjq
 * @date 2019-10-15.
 * description：
 */
class LiveDataCallAdapterFactory : Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType)!= LiveData::class.java) return null
        //获取第一个泛型
        val observaleType = getParameterUpperBound(0,returnType as ParameterizedType)
        val rawType = getRawType(observaleType)
        if (rawType!= ApiResponse::class.java) {
            throw IllegalAccessException("type must be ApiResponse")
        }
        if (observaleType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }

        return LiveDataCallAdapter<Any>(observaleType)
    }
}