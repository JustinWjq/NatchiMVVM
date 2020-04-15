package com.wanandroid.natchikotlin.ui.home.adpter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用模块:
 * <p>
 * 类描述: FragmentPagerAdapter ：会留在内存中 用于更少的页面
 *
 *        FragmentStatePagerAdapter ：离开当前页面 会释放资源  多的页面
 * <p>
 *
 */
public class MainPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments ;
    public MainPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setData(List<Fragment> data){
        if (fragments == null){
            fragments = new ArrayList<>();
        }
        fragments.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (fragments != null && fragments.size() > 0){
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0){
            return fragments.size();
        }
        return 0;
    }
}
