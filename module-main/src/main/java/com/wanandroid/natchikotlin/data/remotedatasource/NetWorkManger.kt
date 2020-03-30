package com.wanandroid.natchikotlin.data.remotedatasource


/**
 * Created by JustinWjq
 * @date 2019-08-10.
 * description：协程-retrofit 网络库
 * 1。异常处理
 * 2。
 */
class NetWorkManger {

    companion object {

        //单例模式
//        val mNetwork by lazy {
//            NetWorkManger()
//        }

        private var network: NetWorkManger? = null

        fun getInstance(): NetWorkManger {
            if (network == null) {
                synchronized(NetWorkManger::class.java) {
                    if (network == null) {
                        network = NetWorkManger()
                    }
                }

            }
            return network!!
        }
    }

    suspend fun getWeathers(wxid: Int, wxpageid: String) =
        ServiceBuilder.apiService.getWXSingleDetail(wxid, wxpageid).await()

    suspend fun getWxList() = ServiceBuilder.apiService.getWxList().await()


    suspend fun getProjectList(index : Int) =
        ServiceBuilder.apiService.getProjectList(index, "402")

    suspend fun getHomeBanner() =
        ServiceBuilder.apiService.homeBanner().await()

    suspend fun getNaviJson() =
        ServiceBuilder.apiService.getNaviJson().await()

    suspend fun getHotKey() =
        ServiceBuilder.apiService.getHotKey().await()

    suspend fun getQuery(pageId:Int,serachWord:String) =
        ServiceBuilder.apiService.getQuery(pageId,serachWord).await()

    suspend fun getSquare(pageId:Int) =
        ServiceBuilder.apiService.getSquare(pageId).await()

}




