package com.wanandroid.todomodule.application;


import com.wanandroid.common.IModuleInit;
import com.wanandroid.commonlib.base.BaseApplication;

/**
 * 应用模块:
 *
 * todo模块的初始化模块 相当于application
 *
 * @author justin
 * @since 2020-03-30
 */
public class TodoModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(BaseApplication application) {
        return false;
    }

    @Override
    public boolean onInitLow(BaseApplication application) {
        return false;
    }
}
