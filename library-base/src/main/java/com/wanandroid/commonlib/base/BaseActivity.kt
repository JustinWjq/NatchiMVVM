package com.wanandroid.commonlib.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.wanandroid.commonlib.R


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    abstract val layoutId: Int
    lateinit var binding: T

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        BarUtils.setStatusBarLightMode(this,true)//高亮模式 状态栏的颜色为黑色
        BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.Light_Blue))
        ARouter.getInstance().inject(this)
        initView()
    }
}