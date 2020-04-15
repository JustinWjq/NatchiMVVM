package com.wanandroid.natchikotlin.extension

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.wanandroid.natchikotlin.R

/**
 * Created by JustinWjq
 * @date 2020-04-02.
 * description：
 */

/**
* 初始化有返回键的toolbar
*/
fun Toolbar.initView(
    titleStr: String = "",
    backimg: Int = R.drawable.main_ic_back,
    onback: (toolbar: Toolbar) -> Unit
): Toolbar {

    title = titleStr
    setTitleTextColor(ContextCompat.getColor(context,R.color.bg_dark))
    setNavigationIcon(backimg)
    setNavigationOnClickListener { onback.invoke(this) }
    return this
}