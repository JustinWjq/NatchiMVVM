package com.wanandroid.natchikotlin.ui.hot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wanandroid.commonlib.base.LazyLoadFragment

import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.databinding.HotItemFragmentBinding
import com.wanandroid.natchikotlin.ui.hot.adapter.HotItemAdapter
import com.wanandroid.natchikotlin.utils.InjectorUtils

class HotItemFragment(val tabName : String) : LazyLoadFragment<HotItemFragmentBinding>() {

    private val viewModel: HotPageViewModel by viewModels {
        InjectorUtils.provideHotViewModelFactory(requireContext())
    }

    override fun loadData() {
        viewModel.getSerachWord(tabName)


    }
    val adapter = HotItemAdapter()
    override fun getLayoutId(): Int =R.layout.hot_item_fragment
    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding.adapter = adapter
        viewModel.serachWordList.observe(this, Observer {
            adapter.submitList(it.datas)
        })
    }

}
