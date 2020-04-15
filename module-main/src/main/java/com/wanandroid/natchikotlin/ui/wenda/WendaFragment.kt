package com.wanandroid.natchikotlin.ui.wenda


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wanandroid.commonlib.base.LazyLoadFragment
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.databinding.MainWendaFragmentBinding
import com.wanandroid.natchikotlin.net.LoadState
import com.wanandroid.natchikotlin.net.bean.PageItemBean
import com.wanandroid.natchikotlin.ui.home.adpter.HomeQuickAdapter
import com.wanandroid.natchikotlin.ui.hot.adapter.HotItemAdapter
import com.wanandroid.natchikotlin.ui.views.loadCallBack.ErrorCallback
import com.wanandroid.natchikotlin.ui.views.loadCallBack.LoadingCallback
import com.wanandroid.natchikotlin.ui.web.WebActivity
import com.wanandroid.natchikotlin.utils.InjectorUtils


class WendaFragment : LazyLoadFragment<MainWendaFragmentBinding>() {




    private val viewModel: WendaViewModel by viewModels {
        InjectorUtils.provideWendaViewModelFactory(requireContext())
    }

    override fun loadData() {
        viewModel.getTopList(1)
    }

    val mAdapter = HomeQuickAdapter()

    override fun getLayoutId(): Int = R.layout.main_wenda_fragment

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding.adapter = mAdapter
        viewModel.pageItemList.observe(this, Observer {
            when(it){
                is  LoadState.Loading->{
                    loadsir.showCallback(LoadingCallback::class.java)
                }
                is  LoadState.Success->{
                    loadsir.showSuccess()
                    mAdapter.setNewData(it.data)
                }
                is  LoadState.Error->{
                    loadsir.showCallback(ErrorCallback::class.java)
                }
            }
        })


        mAdapter.setOnItemClickListener { adapter, view, position ->
            val itemBean = adapter.data[position] as PageItemBean
            val intent = Intent(activity, WebActivity::class.java).apply {
                putExtra("title",itemBean?.title)
                putExtra("url",itemBean?.link)
            }

            activity?.startActivity(intent)
        }



    }

    override fun onReloadCallBack() {
        super.onReloadCallBack()
        viewModel.getTopList(1)
    }

}
