package com.wanandroid.natchikotlin.ui.dashboard.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.wanandroid.natchikotlin.ui.dashboard.bean.ItemBean

/**
 * Created by JustinWjq
 * @date 2020-03-19.
 * description：
 */
class MultipleItemAdapter :BaseProviderMultiAdapter<ItemBean>() {

    init {
//        addItemProvider()
    }


    override fun getItemType(data: List<ItemBean>, position: Int): Int {
            return 1
    }
}