package com.wanandroid.natchikotlin.extension

/**
 * Created by JustinWjq
 * @date 2020-03-18.
 * description：
 */

fun CharSequence?.checkEmpty(): CharSequence {
    return if (this.isNullOrEmpty()) {
        "暂无"
    } else {
        this
    }

}