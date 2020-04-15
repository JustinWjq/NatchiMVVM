package com.wanandroid.natchikotlin.ui.hot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wanandroid.commonlib.base.LazyLoadFragment
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.databinding.HotFragmentBinding
import com.wanandroid.natchikotlin.ui.hot.adapter.VpAdapter
import com.wanandroid.natchikotlin.utils.InjectorUtils


/**
 * Created by JustinWjq
 * @date 2019-10-31.
 * description：
 */
class HotFragment : LazyLoadFragment<HotFragmentBinding>() {
    override fun loadData() {
        viewModel.getHotKey()
    }

    private val viewModel: HotPageViewModel by viewModels {
        InjectorUtils.provideHotViewModelFactory(requireContext())
    }

    override fun getLayoutId(): Int = R.layout.hot_fragment

    private var titles= ArrayList< String>()

    val fragments = ArrayList<LazyLoadFragment<*>>()


    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        titles.clear()
        fragments.clear()
        viewModel.hotKeyList.observe(this, Observer { it ->
            it.forEach {
                titles.add(it.name)
                fragments.add(HotItemFragment(it.name))
            }
            initViewPager()
        })


    }

    private fun initViewPager() {

        mBinding.run {
            viewPager.adapter = VpAdapter(childFragmentManager, fragments)
            viewPager.offscreenPageLimit = fragments.size
            tabLayout.setViewPager(viewPager, titles.toTypedArray() )
            tabLayout.showMsg(2, 3)
        }
    }
}