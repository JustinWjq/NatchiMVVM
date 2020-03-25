package com.wanandroid.natchikotlin.ui.home.adpter

import android.text.Html
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.wanandroid.natchikotlin.data.bean.BannerBean
import com.wanandroid.natchikotlin.extension.displayWithUrl



/**
 * Created by JustinWjq
 * @date 2019-10-15.
 * description：@BindingAdapter 传参（一个是View，一个是设置的参数）
 */
@BindingAdapter("img")
public fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url).apply(RequestOptions.centerCropTransform())
        .into(view)
}

/**
 * 绑定 Banner 图片列表和点击事件
 * @param banners Banner 信息列表
 * @param listener 点击事件
 */
@BindingAdapter(value = ["banners1", "bannerClick"], requireAll = false)
fun loadBannerImg(
    banner: BGABanner,
    bannersDataList:MutableLiveData<List<BannerBean>>?,
    listener: BGABanner.Delegate<ImageView, BannerBean>
) {

    if (bannersDataList?.value.isNullOrEmpty()) return
    val adapter: BGABanner.Adapter<ImageView, BannerBean> =
        BGABanner.Adapter { _, image,
                            model,
                            _ ->
            image.displayWithUrl(model!!.imagePath)
        }
    banner.run {
        setAdapter(adapter)
        setDelegate(listener)
    }
    bannersDataList?.value.run {
        banner.setData(this, null)
    }


//    banner.setImages(images)
//        .setImageLoader(GlideLoader())
//        .setBannerStyle(BannerConfig.RIGHT)
//        .setDelayTime(5000).start()
//
//    banner.setOnBannerListener(listener)
}

/**
 * 绑定 ImageView 图片
 * @param banners Banner 信息列表
 * @param listener 点击事件
 */
@BindingAdapter("image")
fun loadBannerImg(imageView: ImageView, imageId: Int) {
    imageView.background = ContextCompat.getDrawable(imageView.context, imageId)
}

/**
 * 绑定 webview 的 url
 */
@BindingAdapter("url")
fun bindWebUrl(webView: WebView, url: String?) {
    if (url.isNullOrBlank()) return
    webView.loadUrl(url)
}

@BindingAdapter(
    value = ["refreshing", "moreLoading", "hasMore"],
    requireAll = false
)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    refreshing: Boolean,
    moreLoading: Boolean,
    hasMore: Boolean

) {
    if (!refreshing) smartLayout.finishRefresh()
    if (!moreLoading) smartLayout.finishLoadMore()
    smartLayout.setNoMoreData(!hasMore)
}

@BindingAdapter(
    value = ["autoRefresh"]
)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    autoRefresh: Boolean
) {
    if (autoRefresh) smartLayout.autoRefresh()
}

@BindingAdapter(
    value = ["onRefreshListener", "onLoadMoreListener"],
    requireAll = false
)
fun bindListener(
    smartLayout: SmartRefreshLayout,
    refreshListener: OnRefreshListener?,
    loadMoreListener: OnLoadMoreListener?
) {
    smartLayout.setOnRefreshListener(refreshListener)
    smartLayout.setOnLoadMoreListener(loadMoreListener)
}


/**
 * 绑定 webview 的 url
 */
@BindingAdapter("texthtml")
fun bindTextHtml(textView: TextView, htmlStr: String?) {
    if (htmlStr.isNullOrBlank()) return
    textView.text = Html.fromHtml(htmlStr)
}