package com.wanandroid.natchikotlin.data.bean

/**
 * @author kuky.
 * @description
 */


data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)