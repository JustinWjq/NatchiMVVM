package com.wanandroid.todomodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.common.router.RouterActivityPath
import com.wanandroid.commonlib.base.LazyLoadFragment
import com.wanandroid.todomodule.databinding.TodoDashboardFragmentBinding




@Route(path = RouterActivityPath.Main.PAGER_MAIN_TODO)
class TodoFragment : LazyLoadFragment<TodoDashboardFragmentBinding>() {

    override fun initFragment(view: View, savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.todo_dashboard_fragment

    override fun loadData() {

    }



}