package com.wanandroid.natchikotlin.ui.views.loadCallBack

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.ui.views.RadarView


class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun getSuccessVisible(): Boolean {
        return super.getSuccessVisible()
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }

    var radarView :RadarView ?=null
    override fun onAttach(context: Context?, view: View?) {
        super.onAttach(context, view)
        radarView = rootView.findViewById<RadarView>(R.id.loading_progress)
        radarView?.start()
    }

    override fun onDetach() {
        super.onDetach()
        radarView?.stop()
    }
}
