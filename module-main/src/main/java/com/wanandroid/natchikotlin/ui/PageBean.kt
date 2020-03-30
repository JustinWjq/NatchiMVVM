package com.wanandroid.natchikotlin.ui

/**
 * Created by JustinWjq
 * @date 2019-11-06.
 * description：
 */
data class PageBean<T>(
    var curPage: Int,
    var datas: List<T>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)