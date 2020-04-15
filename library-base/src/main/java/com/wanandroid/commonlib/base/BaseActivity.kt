package com.wanandroid.commonlib.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.wanandroid.commonlib.R
import android.graphics.ColorMatrixColorFilter
import android.graphics.ColorMatrix
import android.graphics.Paint
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    abstract val layoutId: Int
    lateinit var binding: T

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //全局颜色变灰
        val mPaint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, mPaint)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        BarUtils.setStatusBarLightMode(this,true)//高亮模式 状态栏的颜色为黑色
        BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.White))
        ARouter.getInstance().inject(this)
        initView()
    }
}