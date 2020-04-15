package com.wanandroid.person.personal

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.commonlib.base.LazyLoadFragment
import com.wanandroid.person.R
import com.wanandroid.person.databinding.PersonalFragmentBinding
import com.wanandroid.common.router.PersonRouterActivityPath

@Route(path = PersonRouterActivityPath.Person.PAGER_MAIN)
class PersonalFragment : LazyLoadFragment<PersonalFragmentBinding>() {
    override fun loadData() {

    }

    override fun getLayoutId(): Int {
        return R.layout.personal_fragment
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {

    }



}