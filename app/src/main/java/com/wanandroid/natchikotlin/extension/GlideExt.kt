package com.wanandroid.natchikotlin.extension

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wanandroid.natchikotlin.R

/**
 * Created by JustinWjq
 * @date 2019-10-17.
 * description：扩展类
 */


fun ImageView.displayWithUrl(url: String,
                          placeHolder: Int = R.drawable.logo) {
    Glide.with(this)
        .load(url)
        .placeholder(placeHolder)
        .into(this)
}

