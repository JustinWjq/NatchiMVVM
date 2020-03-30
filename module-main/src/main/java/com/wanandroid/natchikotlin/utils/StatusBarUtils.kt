//package com.wanandroid.natchikotlin.com.wanandroid.commonlib.utils
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.Context
//import android.graphics.Color
//import android.os.Build
//import android.view.View
//import android.view.ViewGroup
//import android.view.WindowManager
//import android.widget.LinearLayout
//import androidx.annotation.ColorInt
//import androidx.annotation.ColorRes
//import androidx.drawerlayout.widget.DrawerLayout
//import com.wanandroid.natchikotlin.R
//
///**
// * Created by JustinWjq
// * @date 2019-11-11.
// * description：
// */
//class StatusBarUtils{
//    /**
//     * 状态栏id
//     */
//    private const val STATUS_BAR_UTILS_STATUS_BAR_VIEW_ID = R.id.status_bar_utils_status_bar_view
//
//    /**
//     * 主布局id
//     */
//    private const val STATUS_BAR_UTILS_CONTENT_VIEW_ID = R.id.status_bar_utils_content_view
//
//    /**
//     * 设置状态栏颜色
//     */
//    fun setStatusBarColorRes(activity: Activity, @ColorRes colorRes: Int) {
//        setStatusBarColor(activity, activity.resources.getColor(colorRes))
//    }
//
//    /**
//     * 设置状态栏颜色
//     */
//    fun setStatusBarColor(activity: Activity, @ColorInt color: Int) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTransparentForWindow(activity)
//            findDrawerLayout(activity)?.also {
//                setDrawLayouterStatusBarColor(activity, it, color)
//            } ?: addPreviousSetting(activity, color)
//        }
//    }
//
//    /**
//     * 去除状态栏（即状态栏透明，整体布局与状态栏重叠）
//     */
//    fun setStatusBarTraslucent(activity: Activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTransparentForWindow(activity)
//            findDrawerLayout(activity)?.also {
//                setDrawerLayoutProperty(it)
//                clearDrawLayouterStatusBarColor(it)
//            } ?: clearPreviousSetting(activity)
//        }
//    }
//
//    /**
//     * 设置状态栏字体颜色（黑）
//     */
//    fun setLightMode(activity: Activity) {
//        setMIUIStatusBarDarkIcon(activity, true)
//        setMeizuStatusBarDarkIcon(activity, true)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        }
//    }
//
//    /**
//     * 设置状态栏字体颜色（白）
//     */
//    fun setDarkMode(activity: Activity) {
//        setMIUIStatusBarDarkIcon(activity, false)
//        setMeizuStatusBarDarkIcon(activity, false)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        }
//    }
//
//    /**
//     * 获取状态栏高度
//     */
//    fun getStatusBarHeight(context: Context): Int {
//        return context.resources.getDimensionPixelSize(context.resources.getIdentifier("status_bar_height", "dimen", "android"))
//    }
//
//    /**
//     * 设置状态栏透明
//     */
//    private fun setTransparentForWindow(activity: Activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            activity.window.also {
//                it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                it.statusBarColor = Color.TRANSPARENT
//                it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            }
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            activity.window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        }
//    }
//
//    private fun findDrawerLayout(activity: Activity): DrawerLayout? {
//        return activity.window.decorView.findViewById<ViewGroup>(android.R.id.content).getChildAt(0).run {
//            this as? DrawerLayout
//        }
//    }
//
//    private fun setDrawLayouterStatusBarColor(activity: Activity, drawerLayout: DrawerLayout, @ColorInt color: Int) {
//        val contentLayout = drawerLayout.getChildAt(0)
//        contentLayout.findViewById<View>(STATUS_BAR_UTILS_STATUS_BAR_VIEW_ID)?.also {
//            if (it.visibility == View.GONE) {
//                it.visibility = View.VISIBLE
//            }
//            it.setBackgroundColor(color)
//        } ?: run {
//            drawerLayout.removeView(contentLayout)
//            val linearLayout = LinearLayout(activity).apply {
//                layoutParams = DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT)
//                orientation = LinearLayout.VERTICAL
//                addView(createStatusBarView(activity, color))
//                addView(contentLayout)
//            }
//            drawerLayout.addView(linearLayout, 0)
//        }
//        setDrawerLayoutProperty(drawerLayout)
//    }
//
//    private fun clearDrawLayouterStatusBarColor(drawerLayout: DrawerLayout) {
//        drawerLayout.findViewById<View>(STATUS_BAR_UTILS_STATUS_BAR_VIEW_ID)?.also {
//            if (it.visibility == View.VISIBLE) {
//                it.visibility = View.GONE
//            }
//        }
//    }
//
//    private fun addPreviousSetting(activity: Activity, @ColorInt color: Int) {
//        val root = activity.window.decorView.findViewById<ViewGroup>(android.R.id.content)
//        root.findViewById<LinearLayout>(STATUS_BAR_UTILS_CONTENT_VIEW_ID)?.apply {
//            findViewById<View>(STATUS_BAR_UTILS_STATUS_BAR_VIEW_ID)?.also {
//                if (it.visibility == View.GONE) {
//                    it.visibility = View.VISIBLE
//                }
//                it.setBackgroundColor(color)
//            } ?: addView(createStatusBarView(activity, color), 0)
//        } ?: run {
//            val content = root.getChildAt(0)
//            root.removeView(content)
//            val linearLayout = LinearLayout(activity).apply {
//                id = STATUS_BAR_UTILS_CONTENT_VIEW_ID
//                layoutParams = DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT)
//                orientation = LinearLayout.VERTICAL
//                addView(createStatusBarView(activity, color))
//                addView(content)
//            }
//            root.addView(linearLayout, 0)
//        }
//    }
//
//    private fun clearPreviousSetting(activity: Activity) {
//        activity.window.decorView.findViewById<View>(STATUS_BAR_UTILS_STATUS_BAR_VIEW_ID)?.also {
//            if (it.visibility == View.VISIBLE) {
//                it.visibility = View.GONE
//            }
//        }
//    }
//
//    /**
//     * 创建伪状态栏
//     */
//    private fun createStatusBarView(activity: Activity, @ColorInt color: Int): View {
//        return View(activity).apply {
//            this.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
//            this.setBackgroundColor(color)
//            this.id = STATUS_BAR_UTILS_STATUS_BAR_VIEW_ID
//        }
//    }
//
//    private fun setDrawerLayoutProperty(drawerLayout: DrawerLayout) {
//        drawerLayout.fitsSystemWindows = false
//        drawerLayout.getChildAt(1).fitsSystemWindows = false
//    }
//
//    /**
//     * 修改 MIUI V6  以上状态栏颜色
//     */
//    @SuppressLint("PrivateApi")
//    private fun setMIUIStatusBarDarkIcon(activity: Activity, darkIcon: Boolean) {
//        val clazz = activity.window.javaClass
//        try {
//            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
//            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
//            val darkModeFlag = field.getInt(layoutParams)
//            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
//            extraFlagField.invoke(activity.window, if (darkIcon) darkModeFlag else 0, darkModeFlag)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    /**
//     * 修改魅族状态栏字体颜色 Flyme 4.0
//     */
//    private fun setMeizuStatusBarDarkIcon(activity: Activity, darkIcon: Boolean) {
//        try {
//            val lp = activity.window.attributes
//            val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
//            val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
//            darkFlag.isAccessible = true
//            meizuFlags.isAccessible = true
//            val bit = darkFlag.getInt(null)
//            var value = meizuFlags.getInt(lp)
//            value = if (darkIcon) {
//                value or bit
//            } else {
//                value and bit.inv()
//            }
//            meizuFlags.setInt(lp, value)
//            activity.window.attributes = lp
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}