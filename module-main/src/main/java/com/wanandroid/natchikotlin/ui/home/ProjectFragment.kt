package com.wanandroid.natchikotlin.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import cn.bingoogolapple.bgabanner.BGABanner
import com.wanandroid.natchikotlin.base.LazyLoadFragment
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.data.bean.BannerBean
import com.wanandroid.natchikotlin.databinding.ProjectFragmentBinding
import com.wanandroid.natchikotlin.ui.details.DetailsFragment
import com.wanandroid.natchikotlin.ui.home.viewmodel.HomePageViewModel
import com.wanandroid.natchikotlin.utils.InjectorUtils
import com.wanandroid.commonlib.utils.LogUtils
import com.wanandroid.natchikotlin.data.LoadState
import com.wanandroid.natchikotlin.data.bean.PageItemBean
import com.wanandroid.natchikotlin.extension.displayWithUrl
import com.wanandroid.natchikotlin.ui.home.adpter.HomeQuickAdapter
import com.wanandroid.natchikotlin.ui.views.loadCallBack.ErrorCallback
import com.wanandroid.natchikotlin.ui.views.loadCallBack.LoadingCallback
import kotlinx.android.synthetic.main.project_fragment.*


class ProjectFragment : LazyLoadFragment<ProjectFragmentBinding>() {

    override fun loadData() {
        LogUtils.d("ProjectFragment","loadData")
        viewModel.initPage()
        viewModel.getBannerList()
    }

    override fun onReloadCallBack() {
        super.onReloadCallBack()
        viewModel.initPage()
    }

    override fun getLayoutId(): Int = R.layout.project_fragment

    private val viewModel: HomePageViewModel by viewModels {
        InjectorUtils.provideHomwListViewModelFactory(requireContext())
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {

        subscribeUi()
    }

    private fun initBanner() {

        val listener = BGABanner.Delegate<ImageView, BannerBean> { banner, itemView, model, position ->
                DetailsFragment.viewDetail(
                    mNavController,
                    R.id.action_navigation_home_to_navigation_project,
                    model!!.url
                )

            }

        val adapter: BGABanner.Adapter<ImageView, BannerBean> =
            BGABanner.Adapter { _, image,
                                model,
                                _ ->
                image.displayWithUrl(model!!.imagePath)
            }

        banner.run {
            setAdapter(adapter)
            setDelegate(listener)
        }
        viewModel.banners.observe(this, Observer {
            banner.setData(it,null)
        })


    }
    val adapter = HomeQuickAdapter()
    private fun initRecylerView(){
        mBinding.adapter = adapter
        viewModel.pageList.observe(this, Observer {
            when(it){
              is  LoadState.Loading->{
                    loadsir.showCallback(LoadingCallback::class.java)
              }
              is  LoadState.Success->{
                  loadsir.showSuccess()
                  adapter.setNewData(it.data)
              }
              is  LoadState.Error->{
                  loadsir.showCallback(ErrorCallback::class.java)
              }
            }


        })

        adapter.setOnItemClickListener { adapter, view, position ->
           val itemBean = adapter.data[position] as PageItemBean
            DetailsFragment.viewDetail(
                mNavController,
                R.id.action_navigation_home_to_navigation_project,
                itemBean.link!!
            )
        }
    }



    private fun subscribeUi() {

        mBinding.vm = viewModel
//        mBinding.executePendingBindings() 立即执行


        initRecylerView()
        initBanner()



        viewModel.page.observe(this, Observer {
            LogUtils.d("","pageindex---$it")

            viewModel.getTopList(it)
        })
    }

    fun NavController.navigateSafe(
        @IdRes resId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navExtras: Navigator.Extras? = null
    ) {
        val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
        if (action != null && currentDestination?.id != action.destinationId) {
            navigate(resId, args, navOptions, navExtras)
        }
    }

}