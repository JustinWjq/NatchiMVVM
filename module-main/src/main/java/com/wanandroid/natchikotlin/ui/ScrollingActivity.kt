package com.wanandroid.natchikotlin.ui

import android.animation.ValueAnimator
import android.util.Log
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.commonlib.base.BaseActivity
import com.wanandroid.natchikotlin.ARouterPath
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.databinding.ScrollingActivityBinding

import kotlinx.android.synthetic.main.scrolling_activity.*

@Route(path = ARouterPath.SCROLLPATH)
class ScrollingActivity(override val layoutId: Int=R.layout.scrolling_activity)
    : BaseActivity<ScrollingActivityBinding>(),ItemListDialogFragment.Listener {

    override fun onItemClicked(position: Int) {

    }

    override fun initView() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            startAnimator()
            ItemListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
        }


    }

    fun startAnimator() {
        val anim = ValueAnimator.ofInt(0, 200)
        anim.duration = 1000
        anim.addUpdateListener {
            Log.i("addUpdateListener","${it.animatedValue}")
        }

        anim.start()
    }

}
