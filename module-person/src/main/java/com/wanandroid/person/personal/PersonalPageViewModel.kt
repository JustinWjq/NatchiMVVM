package com.wanandroid.person.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalPageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is personal Fragment"
    }
    val text: LiveData<String> = _text
}