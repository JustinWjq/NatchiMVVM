package com.wanandroid.natchikotlin.ui.home.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wanandroid.natchikotlin.net.bean.HomeTabBean
import com.wanandroid.natchikotlin.databinding.HometabTablayoutItemBinding


/**
 * Created by JustinWjq
 * @date 2019-10-15.
 * description：
 */
class HomeTabAdapter : ListAdapter<HomeTabBean, RecyclerView.ViewHolder>(HomeTabCallback()) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      val item =  getItem(position)
        (holder as HomeViewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            HometabTablayoutItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }




    class HomeViewHolder(
        private val binding: HometabTablayoutItemBinding

    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {

            }
        }
        fun bind(item: HomeTabBean) {
            binding.apply {
                viewModel = item
                executePendingBindings()
            }
        }

        private fun navigateToDetails(item: HomeTabBean, it :View){


        }
    }
}

private class HomeTabCallback : DiffUtil.ItemCallback<HomeTabBean>() {

    override fun areItemsTheSame(oldItem: HomeTabBean, newItem: HomeTabBean): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: HomeTabBean, newItem: HomeTabBean): Boolean {
        return true
    }
}