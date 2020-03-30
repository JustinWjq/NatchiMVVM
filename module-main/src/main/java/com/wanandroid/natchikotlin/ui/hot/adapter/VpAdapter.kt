package com.wanandroid.natchikotlin.ui.hot.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.wanandroid.natchikotlin.base.LazyLoadFragment

class VpAdapter( childFragmentManager :FragmentManager,val fragments : ArrayList<LazyLoadFragment<*>>)
    : FragmentStatePagerAdapter(childFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

    }