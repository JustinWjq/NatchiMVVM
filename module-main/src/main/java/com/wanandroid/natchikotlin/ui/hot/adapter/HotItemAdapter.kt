package com.wanandroid.natchikotlin.ui.hot.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wanandroid.natchikotlin.net.bean.PageItemBean
import com.wanandroid.natchikotlin.databinding.HotitemRecycleItemBinding


/**
 * Created by JustinWjq
 * @date 2019-10-15.
 * description：
 */
class HotItemAdapter : ListAdapter<PageItemBean, RecyclerView.ViewHolder>(HomeDiffCallback()) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      val item =  getItem(position)
        (holder as HomeViewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(HotitemRecycleItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }




    class HomeViewHolder(
        private val binding: HotitemRecycleItemBinding

    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {

            }
        }
        fun bind(item: PageItemBean) {
            binding.apply {
                viewModel = item
                executePendingBindings()
            }
        }

        private fun navigateToDetails(item: PageItemBean, it :View){


        }
    }
}

private class HomeDiffCallback : DiffUtil.ItemCallback<PageItemBean>() {

    override fun areItemsTheSame(oldItem: PageItemBean, newItem: PageItemBean): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: PageItemBean, newItem: PageItemBean): Boolean {
        return true
    }
}