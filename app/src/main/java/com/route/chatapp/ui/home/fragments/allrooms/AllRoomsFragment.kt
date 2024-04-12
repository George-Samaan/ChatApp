package com.route.chatapp.ui.home.fragments.allrooms

import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseFragment
import com.route.chatapp.databinding.FragmentAllRoomsBinding


class AllRoomsFragment : BaseFragment<FragmentAllRoomsBinding, AllRoomsViewModel>() {
    override fun initViewModel(): AllRoomsViewModel {
        return ViewModelProvider(this)[AllRoomsViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_all_rooms
    }

}