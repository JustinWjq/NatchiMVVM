package com.wanandroid.natchikotlin.ui.home.adpter


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.natchikotlin.R

/**
 * 文 件 名: MultipleItemQuickAdapter
 * 创 建 人: Allen
 * 创建日期: 2017/6/14 14:05
 * 修改时间：
 * 修改备注：
 */
class MultipleItemQuickAdapter(data: ArrayList<MultipleItem>) : BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {

    init {
        addItemType(1, R.layout.demo)
        addItemType(2, R.layout.demo1)
        addItemType(3, R.layout.demo)
    }

    override fun convert(helper: BaseViewHolder, item: MultipleItem) {

    }

}
