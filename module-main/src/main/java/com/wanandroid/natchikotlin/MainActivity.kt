package com.wanandroid.natchikotlin

import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.wanandroid.commonlib.base.BaseActivity
import com.wanandroid.natchikotlin.databinding.MainActivityBinding
import com.wanandroid.common.router.MainRouterActivityPath
import com.wanandroid.common.router.PersonRouterActivityPath
import com.wanandroid.common.router.RouterActivityPath
import com.wanandroid.natchikotlin.ui.home.adpter.MainPageAdapter
import kotlinx.android.synthetic.main.main_activity.*
import me.majiajie.pagerbottomtabstrip.NavigationController
import java.util.ArrayList

@Route(path = MainRouterActivityPath.Main.PAGER_MAIN)
class MainActivity : BaseActivity<MainActivityBinding>() {



    override fun initView() {
        //barutil中的fakeStatusBar是自己定义的所谓状态栏的范围和背景
        BarUtils.setStatusBarColor4Drawer(binding.drawerlayout,
            binding.view,ContextCompat.getColor(this,R.color.White),true)
        binding.fab.setOnClickListener {
            binding.drawerlayout.openDrawer(GravityCompat.START)
        }

        //初始化底部导航栏
        initBottomView()
        initFragmentList()




    }

    private fun initFragmentList() {
        fragments = ArrayList()
        //通过ARouter 获取其他组件提供的fragment
        val homeFragment =
            ARouter.getInstance().build(MainRouterActivityPath.Main.PAGER_MAIN_HOME).navigation() as Fragment
        val todoFragment =
            ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN_TODO).navigation() as Fragment
        val personFragment =
            ARouter.getInstance().build(PersonRouterActivityPath.Person.PAGER_MAIN).navigation() as Fragment

        fragments?.apply {
            add(homeFragment)
            add(todoFragment)
            add(personFragment)
        }

        adapter1?.setData(fragments)
    }

    private var fragments: MutableList<Fragment>? = null

    private var adapter1: MainPageAdapter? = null

    private var mNavigationController: NavigationController? = null

    private fun initBottomView() {
        mNavigationController = nav_view.material()
            .addItem(
                R.drawable.main_ic_home_black_24dp,
                "首页",
                ColorUtils.getColor(R.color.textColorPrimary)
            )
            .addItem(
                R.drawable.main_ic_dashboard_black_24dp,
                "todo",
                ColorUtils.getColor( R.color.textColorPrimary)
            )
            .addItem(
                R.drawable.main_ic_notifications_black_24dp,
                "我的",
                ColorUtils.getColor(R.color.textColorPrimary)
            )
            .setDefaultColor(
                ColorUtils.getColor( R.color.textColorPrimary)
            )
            .enableAnimateLayoutChanges()
            .build()
        mNavigationController?.apply {
            setHasMessage(1, true)
            setMessageNumber(2, 6)
        }

        adapter1 = MainPageAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
        )
        nav_host_fragment.apply {
            offscreenPageLimit = 1
            adapter = adapter1 //拓展函数内名字千万不要取一样的，不然就掉坑里了
        }

        mNavigationController?.setupWithViewPager(nav_host_fragment)
    }

    override val layoutId: Int
        get() = R.layout.main_activity





}
