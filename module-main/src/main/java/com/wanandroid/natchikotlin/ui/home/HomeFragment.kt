package com.wanandroid.natchikotlin.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.natchikotlin.R
import com.wanandroid.commonlib.base.LazyLoadFragment
import com.wanandroid.natchikotlin.databinding.MainHomeFragmentBinding
import com.wanandroid.common.router.MainRouterActivityPath
import com.wanandroid.natchikotlin.ui.home.adpter.MainPageAdapter


import com.wanandroid.natchikotlin.ui.home.viewmodel.HomePageViewModel
import com.wanandroid.natchikotlin.ui.hot.HotFragment
import com.wanandroid.natchikotlin.ui.knowledgesystem.KnowSystemFragment
import com.wanandroid.natchikotlin.ui.square.SquareFragment
import com.wanandroid.natchikotlin.ui.wenda.WendaFragment
import com.wanandroid.natchikotlin.utils.InjectorUtils


/**
 * Created by JustinWjq
 * @date 2019-10-31.
 * description：
 */

@Route(path = MainRouterActivityPath.Main.PAGER_MAIN_HOME)
class HomeFragment : LazyLoadFragment<MainHomeFragmentBinding>() {
    override fun loadData() {

    }

    private val viewModel: HomePageViewModel by viewModels {
        InjectorUtils.provideHomwListViewModelFactory(requireContext())
    }



    override fun onReloadCallBack() {
        super.onReloadCallBack()

    }




    override fun getLayoutId(): Int = R.layout.main_home_fragment

    private val titles = arrayOf("项目", "热门","广场","知识体系","每日一问")
    val fragments
            = arrayListOf(ProjectFragment(),
        HotFragment(), SquareFragment(),KnowSystemFragment(),WendaFragment()
    )


    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        initViewPager()
    }

    var adapter1 : MainPageAdapter ? =null
    private fun initViewPager() {
        adapter1 = MainPageAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT)
        adapter1?.setData(fragments as List<Fragment>?)
        mBinding.run {
            viewPager.adapter = adapter1
            viewPager.offscreenPageLimit = fragments.size
            tabLayout.setViewPager(viewPager, titles)
            tabLayout.showMsg(2,3)
        }
        if (!viewModel.isLoad.value!!){

            viewModel.isLoad.value = true
        }


    }


}