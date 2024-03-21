package com.route.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.route.chatapp.R
import com.route.chatapp.base.BaseFragment
import com.route.chatapp.databinding.ActivityHomeBinding
import com.route.chatapp.ui.home.adapter.RoomsViewPagerAdapter
import com.route.chatapp.ui.roomdetails.RoomDetailsActivity

class HomeFragment : BaseFragment<ActivityHomeBinding, HomeViewModel>() {

    val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRoomsViewPager()
        binding.fabAddRoom.setOnClickListener {
            navigateToRoomDetails()
        }
    }

    override fun initViewModel(): HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    override fun getLayoutId(): Int = R.layout.activity_home

    private fun initRoomsViewPager() {
        val adapter = RoomsViewPagerAdapter(requireActivity())
        binding.roomsViewPager.adapter = adapter
        TabLayoutMediator(binding.roomsTabLayout, binding.roomsViewPager) { tab, position ->
            val tabTitles =
                resources?.getStringArray(R.array.rooms_fragments_titles) ?: emptyArray()
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun navigateToRoomDetails() {
        startActivity(Intent(activity, RoomDetailsActivity::class.java))
    }


}


