package com.wanandroid.natchikotlin.ui.dashboard.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Created by JustinWjq
 * @date 2020-03-09.
 * description：
 */
class ItemBean(
    var itemType1: Int,
    var content: String,
    override val itemType: Int
) : MultiItemEntity