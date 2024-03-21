package com.route.chatapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.chatapp.ui.ViewMessage

open class BaseViewModel : ViewModel() {
    val viewMessage = MutableLiveData<ViewMessage>()

}