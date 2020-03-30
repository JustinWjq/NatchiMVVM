package com.wanandroid.natchikotlin.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wanandroid.natchikotlin.base.LazyLoadFragment
import com.wanandroid.natchikotlin.R

import com.wanandroid.natchikotlin.databinding.HomeFragmentBinding
import com.wanandroid.natchikotlin.ui.home.viewmodel.HomePageViewModel
import com.wanandroid.natchikotlin.ui.hot.HotFragment
import com.wanandroid.natchikotlin.ui.hot.adapter.VpAdapter
import com.wanandroid.natchikotlin.ui.knowledgesystem.KnowSystemFragment
import com.wanandroid.natchikotlin.ui.square.SquareFragment
import com.wanandroid.natchikotlin.utils.InjectorUtils


/**
 * Created by JustinWjq
 * @date 2019-10-31.
 * description：
 */
class HomeFragment : LazyLoadFragment<HomeFragmentBinding>() {
    private val viewModel: HomePageViewModel by viewModels {
        InjectorUtils.provideHomwListViewModelFactory(requireContext())
    }

    override fun onReloadCallBack() {
        super.onReloadCallBack()

    }

    override fun loadData() {

    }


    override fun getLayoutId(): Int = R.layout.home_fragment

    private val titles = arrayOf("项目", "热门","广场","知识体系")
    val fragments
            = arrayListOf(ProjectFragment(),
        HotFragment(), SquareFragment(),
        KnowSystemFragment()
    )


    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        initViewPager()
    }

    private fun initViewPager() {
        mBinding.run {
            viewPager.adapter = VpAdapter(childFragmentManager,fragments)
            viewPager.offscreenPageLimit = fragments.size
            tabLayout.setViewPager(viewPager, titles)
            tabLayout.showMsg(2,3)
        }
        if (!viewModel.isLoad.value!!){

            viewModel.isLoad.value = true
        }


    }


}