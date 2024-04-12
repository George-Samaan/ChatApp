package com.route.chatapp.ui.home.fragments.myrooms

import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseFragment
import com.route.chatapp.databinding.FragmentMyRoomsBinding


class MyRoomsFragment : BaseFragment<FragmentMyRoomsBinding, MyRoomsViewModel>() {
    override fun initViewModel() = ViewModelProvider(this)[MyRoomsViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_my_rooms


}