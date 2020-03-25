package com.wanandroid.natchikotlin.ui.views.loadCallBack


import com.kingja.loadsir.callback.Callback
import com.wanandroid.natchikotlin.R


class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }
}
