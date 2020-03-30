package com.wanandroid.natchikotlin.ui.home.adpter

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.data.bean.PageItemBean
import java.util.HashMap

/**
 * Created by JustinWjq
 * @date 2019-12-04.
 * description：
 */
class HomeQuickAdapter :
    BaseQuickAdapter<PageItemBean, BaseViewHolder>(R.layout.project_recycle_item_test) {


    override fun convert(helper: BaseViewHolder, item: PageItemBean) {
        with(helper) {
            setText(R.id.tv_pj_title, item!!.title)

            setText(R.id.tv_pj_desc, Html.fromHtml(item.desc))
            setText(R.id.tv_chapterName, item.chapterName)
            setText(R.id.tv_author, item.author)
            val imageView = helper.getView<ImageView>(R.id.tv_pj_envelopePic)
//            if (!item.envelopePic.isNullOrEmpty()) {
//                Glide.with(imageView).load(item.envelopePic).into(imageView)
//            }
            getView<TextView>(R.id.tv_top).visibility = when (item.type) {
                1 -> {
                    View.VISIBLE
                }
                else -> {
                    View.GONE
                }
            }
        }
    }
}