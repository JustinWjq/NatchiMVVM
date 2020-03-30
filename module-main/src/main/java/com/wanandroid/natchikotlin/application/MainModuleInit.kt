package com.wanandroid.natchikotlin.application

import com.blankj.utilcode.util.Utils
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.wanandroid.common.IModuleInit
import com.wanandroid.commonlib.base.BaseApplication
import com.wanandroid.natchikotlin.ui.views.loadCallBack.EmptyCallback
import com.wanandroid.natchikotlin.ui.views.loadCallBack.ErrorCallback
import com.wanandroid.natchikotlin.ui.views.loadCallBack.LoadingCallback


/**
 * 应用模块: main
 *
 *
 * 类描述: main组件的业务初始化
 *
 *
 *
 * @author darryrzhoong
 * @since 2020-02-26
 */
class MainModuleInit : IModuleInit {
    override fun onInitAhead(application: BaseApplication): Boolean {
        //        ScreenAutoAdapter.setup(application);
        //        EasyHttp.init(application);
        //        if (application.issDebug())
        //        {
        //            EasyHttp.getInstance().debug("easyhttp", true);
        //        }
        //        EasyHttp.getInstance()
        //            .setBaseUrl("http://baobab.kaiyanapp.com")
        //            .setReadTimeOut(15 * 1000)
        //            .setWriteTimeOut(15 * 1000)
        //            .setConnectTimeout(15 * 1000)
        //            .setRetryCount(3)
        //            .setCacheDiskConverter(new GsonDiskConverter())
        //            .setCacheMode(CacheMode.FIRSTREMOTE);
        //        LoadSir.beginBuilder()
        //            .addCallback(new ErrorCallback())
        //            .addCallback(new LoadingCallback())
        //            .addCallback(new EmptyCallback())
        //            .addCallback(new TimeoutCallback())
        //            .setDefaultCallback(LoadingCallback.class)
        //            .commit();
        //         Utils.init(application);
        //        Logger.i("main组件初始化完成 -- onInitAhead");
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()
        return false
    }

    override fun onInitLow(application: BaseApplication): Boolean {
        return false
    }
}
