package com.wanandroid.natchikotlin.ui.square

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.wanandroid.commonlib.base.BaseFragment
import com.wanandroid.commonlib.base.LazyLoadFragment
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.databinding.ProjectFragmentBinding
import com.wanandroid.natchikotlin.databinding.SquareFragmentBinding
import com.wanandroid.natchikotlin.ui.hot.adapter.HotItemAdapter
import com.wanandroid.natchikotlin.utils.InjectorUtils
import kotlinx.android.synthetic.main.personal_fragment.*

class SquareFragment : LazyLoadFragment<SquareFragmentBinding>() {

    override fun loadData() {
        viewModel.getSerachWord(0)
    }

    private val viewModel: SquareViewModel by viewModels {
        InjectorUtils.provideSquareViewModelFactory(requireContext())
    }

    val adapter = HotItemAdapter()

    override fun getLayoutId(): Int {
        return R.layout.square_fragment
    }


    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding.adapter = adapter
        viewModel.pageItemList.observe(this, Observer {
            adapter.submitList(it.datas)
        })
    }



}