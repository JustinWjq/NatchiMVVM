package com.wanandroid.natchikotlin.data.bean

/**
 * Created by JustinWjq
 * @date 2019-11-27.
 * description：
 */
data class NavItemBean(
    val articles: List<NavItemInfoBean>,
    val cid: Int,
    val name: String
)

data class NavItemInfoBean (

//    val tags:[],
    val title: String
)