package com.wanandroid.natchikotlin.data.bean

/**
 * Created by JustinWjq
 * @date 2019-11-06.
 * description：
 */
data class PageBean<T>(
    var curPage: Int,
    var datas: MutableList<T>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)