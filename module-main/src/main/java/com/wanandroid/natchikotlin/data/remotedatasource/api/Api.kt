package com.wanandroid.natchikotlin.data.remotedatasource.api

import com.wanandroid.natchikotlin.data.bean.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by JustinWjq
 * @date 2019-08-10.
 * description：
 */
interface Api {
    @GET("/wxarticle/chapters/json")
    fun getWxList(): Call<String>

    @GET("/wxarticle/pageList/{wxid}/{wxpageid}/json")
    fun getWXSingleDetail(
        @Path("wxid") id: Int,
        @Path("wxpageid") wxpageid: String
    ): Call<String>


    //首页文章列表
    @GET("/article/pageList/{id}/json")
    fun getHomePageList(
        @Path("id") id: String
    ): Call<String>


    //4.2 项目列表数据 /project/pageList/{id}/json?cid=294
    @GET("/project/list/{id}/json")
    suspend fun getProjectList(
        @Path("id") id: Int,
        @Query("cid") cid: String
    ): ApiResponse<PageBean<PageItemBean>>

    // 首页 Banner
    @GET("/banner/json")
    fun homeBanner(): Call<ApiResponse<List<BannerBean>>>


    //导航https://www.wanandroid.com/navi/json
    @GET("/navi/json")
    fun getNaviJson(): Call<ApiResponse<List<NavItemBean>>>

    //搜索热词https://www.wanandroid.com/hotkey/json
    @GET("/hotkey/json")
    fun getHotKey(): Call<ApiResponse<List<HotKeyBean>>>

    //搜索热词https://www.wanandroid.com/hotkey/json
    @POST("/article/query/{id}/json")
    fun getQuery(
        @Path("id") pageId: Int,
        @Query("k") serachWord: String
    ): Call<ApiResponse<PageBean<PageItemBean>>>

    //广场列表数据 https://wanandroid.com/user_article/list/0/json
    @GET("/user_article/list/{id}/json")
    fun getSquare(
        @Path("id") pageId: Int): Call<ApiResponse<PageBean<PageItemBean>>>

    @GET("/user_article/list/{id}/json")
    suspend fun getSquare1(
        @Path("id") pageId: Int): ApiResponse<PageBean<PageItemBean>>

    //https://www.wanandroid.com/article/top/json
    @GET("/article/top/json")
    suspend fun getTop(): ApiResponse<MutableList<PageItemBean>>
}

