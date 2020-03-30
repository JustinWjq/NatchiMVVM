package com.wanandroid.natchikotlin.ui.views.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.wanandroid.natchikotlin.R



class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}
