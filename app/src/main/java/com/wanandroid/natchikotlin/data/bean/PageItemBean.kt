package com.wanandroid.natchikotlin.data.bean


class PageItemBean {
    /**
     * apkLink :
     * audit : 1
     * author : xing16
     * chapterId : 294
     * chapterName : 完整项目
     * collect : false
     * courseId : 13
     * desc : WanAndroid-Kotlin是基于 Kotlin + MVP + RxJava + OkHttp 实现好用好看的学习阅读类客户端, 包括首页，项目，体系，干货，搜索，收藏，妹子等功能
     * envelopePic : https://www.wanandroid.com/blogimgs/b4714e97-deb4-4cd4-bf63-e365afe60189.png
     * fresh : false
     * id : 9656
     * link : https://www.wanandroid.com/blog/show/2684
     * niceDate : 2天前
     * niceShareDate : 2天前
     * origin :
     * prefix :
     * projectLink : https://github.com/xing16/WanAndroid-Kotlin
     * publishTime : 1570981232000
     * selfVisible : 0
     * shareDate : 1570981232000
     * shareUser :
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     * tags : [{"name":"项目","url":"/project/pageList/1?cid=294"}]
     * title : Kotlin 实现美观好用的 WanAndroid 客户端
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */

    var apkLink: String? = null
    var audit: Int = 0
    var author: String? = null
    var chapterId: Int = 0
    var chapterName: String? = null
    var isCollect: Boolean = false
    var courseId: Int = 0
    var desc: String? = null
    var envelopePic: String? = null
    var isFresh: Boolean = false
    var id: Int = 0
    var link: String? = null
    var niceDate: String? = null
    var niceShareDate: String? = null
    var origin: String? = null
    var prefix: String? = null
    var projectLink: String? = null
    var publishTime: Long = 0
    var selfVisible: Int = 0
    var shareDate: Long = 0
    var shareUser: String? = null
    var superChapterId: Int = 0
    var superChapterName: String? = null
    var title: String? = null
    var type: Int = 0
    var userId: Int = 0
    var visible: Int = 0
    var zan: Int = 0
    var tags: List<TagsBean>? = null

    class TagsBean {
        /**
         * name : 项目
         * url : /project/pageList/1?cid=294
         */

        var name: String? = null
        var url: String? = null
    }
}