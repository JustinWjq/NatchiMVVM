package com.wanandroid.natchikotlin.data.remotedatasource

import com.wanandroid.natchikotlin.BuildConfig
import com.wanandroid.natchikotlin.data.bean.ApiResponse
import com.wanandroid.natchikotlin.data.remotedatasource.adapter.LiveDataCallAdapterFactory
import com.wanandroid.natchikotlin.data.remotedatasource.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by JustinWjq
 * @date 2019-08-10.
 * description：Retrofit 配置
 */

object ServiceBuilder {

    private const val BASE_URL ="https://www.wanandroid.com"

    private val httpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
           addInterceptor(loggingInterceptor)
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient.build())
        .addConverterFactory(ScalarsConverterFactory.create())
//        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    public val apiService by lazy { retrofit.create(Api::class.java) }



}
fun <T> ApiResponse<T>.disposeData() : T?{

    return if (this.errorCode==0) this.data else throw Throwable(this.errorMsg)
}