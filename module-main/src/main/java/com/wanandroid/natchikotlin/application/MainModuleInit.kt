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

        //初始化blankj的工具类
        Utils.init(application)

        //初始化统一的状态库
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
