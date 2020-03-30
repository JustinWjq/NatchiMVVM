package com.wanandroid.natchikotlin.ui.personal

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.wanandroid.commonlib.base.BaseFragment
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.databinding.ProjectFragmentBinding
import kotlinx.android.synthetic.main.personal_fragment.*

class PersonalFragment : BaseFragment<ProjectFragmentBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.personal_fragment
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        bt_click.setOnClickListener {
            ARouter.getInstance().build("/main/scroll").navigation()
        }
    }



}