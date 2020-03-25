package com.wanandroid.todomodule


import android.app.Application
import android.content.Context
//import com.alibaba.android.arouter.launcher.ARouter


class CommonApplication : Application() {


    companion object {

        var mInstance: CommonApplication? = null
        fun instance() = mInstance!!
    }


    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) {
//            ARouter.openLog()
//            ARouter.openDebug()
//        }
//        ARouter.init(this)
    }

    override fun attachBaseContext(base: Context) {

    }


}

