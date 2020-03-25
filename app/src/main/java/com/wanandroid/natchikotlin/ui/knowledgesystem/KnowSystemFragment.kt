package com.wanandroid.natchikotlin.ui.knowledgesystem


import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryFooterViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryViewHolder
import com.kunminx.linkage.bean.BaseGroupedItem
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig
import com.kunminx.linkage.contract.ILinkageSecondaryAdapterConfig
import com.wanandroid.commonlib.base.LazyLoadFragment
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.data.bean.ElemeGroupedItem
import com.wanandroid.natchikotlin.databinding.KnowledgesFragmentBinding
import com.wanandroid.natchikotlin.utils.InjectorUtils
import kotlinx.android.synthetic.main.knowledges_fragment.*


class KnowSystemFragment : LazyLoadFragment<KnowledgesFragmentBinding>() {

    companion object {
        private val SPAN_COUNT_FOR_GRID_MODE = 2
        private val MARQUEE_REPEAT_LOOP_MODE = -1
        private val MARQUEE_REPEAT_NONE_MODE = 0
    }


    private val viewModel: KnowSystemViewModel by viewModels {
        InjectorUtils.provideKnowSystemViewModelFactory(requireContext())
    }

    override fun loadData() {
        viewModel.getNaviItemList()
    }

    override fun getLayoutId(): Int = R.layout.knowledges_fragment

    val items = ArrayList<ElemeGroupedItem>()
    override fun initFragment(view: View, savedInstanceState: Bundle?) {


        viewModel.pageList.observe(this, Observer{
            items.clear()
            it.forEach{
                val group = it.name
                items.add(ElemeGroupedItem(true,group))

                it.articles.forEach {
                    items.add(
                        ElemeGroupedItem(
                            ElemeGroupedItem.ItemInfo(
                                it.title,
                                group,
                                it.title
                            )
                        )
                    )
                }

            }
            if (items.size!=0){
                linkage.init(
                    items as List<Nothing>?,
                    GreatPhoneLinkagePrimaryAdapterConfig(),
                    GreatPhoneLinkageSecondaryAdapterConfig()
                )
                linkage.isGridMode = true
            }

        })


    }

    private class GreatPhoneLinkagePrimaryAdapterConfig : ILinkagePrimaryAdapterConfig {

        private var mContext: Context? = null

        override fun setContext(context: Context) {
            mContext = context
        }

        override fun getLayoutId(): Int {
            return com.kunminx.linkage.R.layout.default_adapter_linkage_primary
        }

        override fun getGroupTitleViewId(): Int {
            return com.kunminx.linkage.R.id.tv_group
        }

        override fun getRootViewId(): Int {
            return com.kunminx.linkage.R.id.layout_group
        }

        override fun onBindViewHolder(
            holder: LinkagePrimaryViewHolder,
            selected: Boolean,
            title: String
        ) {
            val tvTitle = holder.mGroupTitle as TextView
            tvTitle.text = title

            tvTitle.setBackgroundColor(
                mContext!!.getResources().getColor(
                    if (selected) com.kunminx.linkage.R.color.colorPurple else com.kunminx.linkage.R.color.colorWhite
                )
            )
            tvTitle.setTextColor(
                ContextCompat.getColor(
                    mContext!!,
                    if (selected) com.kunminx.linkage.R.color.colorWhite else com.kunminx.linkage.R.color.colorGray
                )
            )
            tvTitle.ellipsize =
                if (selected) TextUtils.TruncateAt.MARQUEE else TextUtils.TruncateAt.END
            tvTitle.isFocusable = selected
            tvTitle.isFocusableInTouchMode = selected
            tvTitle.marqueeRepeatLimit =
                if (selected) MARQUEE_REPEAT_LOOP_MODE else MARQUEE_REPEAT_NONE_MODE

        }

        override fun onItemClick(holder: LinkagePrimaryViewHolder, view: View, title: String) {
            //TODO
        }
    }

    private class GreatPhoneLinkageSecondaryAdapterConfig :
        ILinkageSecondaryAdapterConfig<ElemeGroupedItem.ItemInfo> {

        private var mContext: Context? = null

        override fun setContext(context: Context) {
            mContext = context
        }

        override fun getGridLayoutId(): Int {
            return R.layout.knowledges_recycle_grid_item
        }

        override fun getLinearLayoutId(): Int {
            return R.layout.knowledges_recycle_linear_item
        }

        override fun getHeaderLayoutId(): Int {
            return com.kunminx.linkage.R.layout.default_adapter_linkage_secondary_header
        }

        override fun getFooterLayoutId(): Int {
            return com.kunminx.linkage.R.layout.default_adapter_linkage_secondary_footer
        }

        override fun getHeaderTextViewId(): Int {
            return R.id.secondary_header
        }

        override fun getSpanCountOfGridMode(): Int {
            return SPAN_COUNT_FOR_GRID_MODE
        }

        override fun onBindViewHolder(
            holder: LinkageSecondaryViewHolder,
            item: BaseGroupedItem<ElemeGroupedItem.ItemInfo>
        ) {

            (holder.getView<View>(R.id.iv_goods_name) as TextView).setText(item.info.getTitle())
//            Glide.with(mContext!!).load(item.info.getImgUrl())
//                .into(holder.getView<View>(R.id.iv_goods_img) as ImageView)
//            holder.getView<View>(R.id.iv_goods_item).setOnClickListener { v ->
//                //TODO
//            }
//
//            holder.getView<View>(R.id.iv_goods_add).setOnClickListener { v ->
//                //TODO
//            }
        }

        override fun onBindHeaderViewHolder(
            holder: LinkageSecondaryHeaderViewHolder,
            item: BaseGroupedItem<ElemeGroupedItem.ItemInfo>
        ) {

            (holder.getView<View>(R.id.secondary_header) as TextView).text = item.header
        }

        override fun onBindFooterViewHolder(
            holder: LinkageSecondaryFooterViewHolder,
            item: BaseGroupedItem<ElemeGroupedItem.ItemInfo>
        ) {
            (holder.getView<View>(R.id.tv_secondary_footer) as TextView).apply {
                text = "无数据"
                gravity = Gravity.CENTER
            }

        }
    }

}
