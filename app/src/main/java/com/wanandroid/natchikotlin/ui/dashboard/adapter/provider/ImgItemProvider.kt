package com.wanandroid.natchikotlin.ui.dashboard.adapter.provider

import android.view.View
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wanandroid.natchikotlin.ui.dashboard.bean.ItemBean

/**
 * Created by JustinWjq
 * @date 2020-03-19.
 * description：
 */
class ImgItemProvider :BaseItemProvider<ItemBean>() {
    override val itemViewType: Int
        get() = 1
    override val layoutId: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun convert(helper: BaseViewHolder, data: ItemBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: ItemBean, position: Int) {
        super.onClick(helper, view, data, position)
    }

    override fun onLongClick(
        helper: BaseViewHolder,
        view: View,
        data: ItemBean,
        position: Int
    ): Boolean {
        return super.onLongClick(helper, view, data, position)
    }
}