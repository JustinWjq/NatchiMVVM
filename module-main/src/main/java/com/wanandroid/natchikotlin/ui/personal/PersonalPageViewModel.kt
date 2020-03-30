package com.wanandroid.natchikotlin.ui.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wanandroid.natchikotlin.base.BasePageViewModel

class PersonalPageViewModel : BasePageViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is personal Fragment"
    }
    val text: LiveData<String> = _text
}