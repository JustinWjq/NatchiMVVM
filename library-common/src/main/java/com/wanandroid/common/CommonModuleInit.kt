package com.wanandroid.common


import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV
import com.wanandroid.commonlib.base.BaseApplication
import com.wanandroid.commonlib.utils.LogUtils

/**
 * 应用模块:
 *
 *
 * 类描述: 通用库 & 基础库 自身初始化操作
 *
 *
 *
 * @author darryrzhoong
 * @since 2020-02-25
 */
class CommonModuleInit : IModuleInit {
    override fun onInitAhead(application: BaseApplication): Boolean {
        // 初始化日志
        //        Logger.addLogAdapter(new AndroidLogAdapter()
        //        {
        //            @Override
        //            public boolean isLoggable(int priority, @Nullable String tag)
        //            {
        //                return application.issDebug();
        //            }
        //        });
        if (application.issDebug()) {
            ARouter.openLog() // 开启日志
            ARouter.openDebug() // 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
        }
        ARouter.init(application)
        MMKV.initialize(application)
        LogUtils.i("CommonModuleInit : onInitAhead")
        return false
    }

    override fun onInitLow(application: BaseApplication): Boolean {
        return false
    }

}
