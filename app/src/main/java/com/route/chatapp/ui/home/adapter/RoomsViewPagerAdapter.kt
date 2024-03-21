package com.route.chatapp.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.route.chatapp.ui.home.fragments.allrooms.AllRoomsFragment
import com.route.chatapp.ui.home.fragments.myrooms.MyRoomsFragment

class RoomsViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> MyRoomsFragment()
            else -> AllRoomsFragment()
        }
        return fragment
    }
}