package com.wanandroid.common.config;

/**
 * 应用模块: common
 * <p>
 * 类描述: 组件生命周期初始化类的类目管理者,在这里注册需要初始化的组件,通过反射动态调用各个组件的初始化方法
 * <p>
 *
 */
public class ModuleLifecycleReflexs
{
    /** 基础库 */
    private static final String BaseInit = "com.wanandroid.common.CommonModuleInit";
    
    /** main组件库 */
    private static final String MainInit =
        "com.wanandroid.natchikotlin.application.MainModuleInit";

    /**用户组件初始化*/
    private static final String TodoModuleInit = "com.wanandroid.todomodule.application.TodoModuleInit";

    private static final String PersonModuleInit = "com.wanandroid.person.application.PersonModuleInit";

    public static String[] initModuleNames = {BaseInit, MainInit,TodoModuleInit};
}
