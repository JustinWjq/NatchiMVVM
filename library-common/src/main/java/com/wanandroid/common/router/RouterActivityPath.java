package com.wanandroid.common.router;

/**
 * 应用模块: 组件化路由
 *
 * 类描述: 用于在组件化开发中,利用ARouter 进行Activity跳转的统一路径注册, 注册时请写好注释、标注界面功能业务
 *
 *
 * @author justin
 * @since 2020-3-30
 */
public class RouterActivityPath
{
    /**
     * main组件
     */
    public static class Main
    {
        private static final String MAIN = "/main";
        
        /** 主页面 */
        public static final String PAGER_MAIN = MAIN + "/Main";

        /**主页fragment*/
        public static final String PAGER_MAIN_HOME = MAIN + "/Home";

        /**todofragment  TodoFragment 不同模块的路径 路径名需要不一致*/
        public static final String PAGER_MAIN_TODO ="/todo/Todo";

        /**PersonalFragment*/
        public static final String PAGER_MAIN_PERSONAL = MAIN + "/Personal";


        /** **/
        public static final String PAGER_MAIN1 = MAIN + "/Main";


    }

    /**
     *
     * todo组件
     *
     * */
    
    public static class Todo
    {
        private static final String TODO = "/todo";
        
        /** 登录界面 */
        public static final String PAGER_LOGIN = TODO + "/Login";
        public static final String PAGER_MAIN = TODO + "/Main";

        /** 关注页面 */
        public static final String PAGER_ATTENTION = TODO + "/attention";
    }
}
