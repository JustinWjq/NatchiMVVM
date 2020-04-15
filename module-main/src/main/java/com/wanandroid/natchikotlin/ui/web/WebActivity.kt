package com.wanandroid.natchikotlin.ui.web

import android.net.http.SslError
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.commonlib.base.BaseActivity
import com.wanandroid.commonlib.utils.LogUtils
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.databinding.DetailsFragmentBinding
import com.wanandroid.natchikotlin.extension.initView
import com.wanandroid.common.router.MainRouterActivityPath
import kotlinx.android.synthetic.main.details_fragment.*


@Route(path = MainRouterActivityPath.Web.MAIN_WEB)
class WebActivity : BaseActivity<DetailsFragmentBinding>() {
    override val layoutId: Int
        get() = R.layout.details_fragment

    override fun initView() {

        val extras = intent.extras


        val title =extras?.getString("title")
        LogUtils.i(title!!)

        setSupportActionBar(toolbar)
        toolbar.run{

            this@WebActivity?.let {
                it.setSupportActionBar(this)
            }
            initView(title){
                finish()
            }
        }


        binding.url = extras.getString("url")

        content.settings.apply {
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
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        content.apply {
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
//                    if (newProgress == 100) loading.visibility = View.GONE
                }
            }

            webViewClient = object : WebViewClient(){
                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    handler?.proceed()
                }
            }
        }
        //添加返回键逻辑
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                   finish()
                }
            }

       onBackPressedDispatcher.addCallback(this,callback)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.web_menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
    }



}