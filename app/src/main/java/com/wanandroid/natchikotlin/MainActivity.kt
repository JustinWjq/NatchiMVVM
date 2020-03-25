package com.wanandroid.natchikotlin

import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.blankj.utilcode.util.BarUtils
import com.wanandroid.commonlib.base.BaseActivity
import com.wanandroid.natchikotlin.databinding.MainActivityBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : BaseActivity<MainActivityBinding>() {


    protected lateinit var mNavController: NavController
    override fun initView() {
        mNavController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(mNavController)
        //barutil中的fakeStatusBar是自己定义的所谓状态栏的范围和背景
        BarUtils.setStatusBarColor4Drawer(binding.drawerlayout,
            binding.view,ContextCompat.getColor(this,R.color.White),true)
        binding.fab.setOnClickListener {
            binding.drawerlayout.openDrawer(GravityCompat.START)
        }


    }

    override val layoutId: Int
        get() = R.layout.main_activity




}
