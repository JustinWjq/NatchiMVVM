package com.wanandroid.natchikotlin.ui.details

import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.wanandroid.commonlib.base.BaseFragment
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.databinding.DetailsFragmentBinding
import kotlinx.android.synthetic.main.details_fragment.view.*

class DetailsFragment : BaseFragment<DetailsFragmentBinding>() {
    override fun getLayoutId(): Int = R.layout.details_fragment

    override fun initFragment(view: View, savedInstanceState: Bundle?) {

        mBinding.url = arguments?.getString("url")

        view.content.settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            allowFileAccess = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            useWideViewPort = true
            loadWithOverviewMode = true
            setSupportMultipleWindows(true)
            setGeolocationEnabled(true)
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            setAppCacheEnabled(true)
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE
        }

        view.content.apply {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true
                }

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return true
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(web: WebView?, newProgress: Int) {
                    super.onProgressChanged(web, newProgress)
                    if (newProgress > 70) view.loading.isVisible = false
                }
            }
        }

    }



    companion object {
        fun viewDetail(controller: NavController, @IdRes id: Int, url: String) {
            if (url.isBlank()) return
            controller.navigate(id, Bundle().apply { putString("url", url) })
        }
    }
}