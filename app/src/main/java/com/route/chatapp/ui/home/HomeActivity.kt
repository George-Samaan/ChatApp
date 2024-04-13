package com.route.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.route.chatapp.R
import com.route.chatapp.base.BaseActivity
import com.route.chatapp.databinding.ActivityHomeBinding
import com.route.chatapp.ui.createroom.RoomCreationActivity
import com.route.chatapp.ui.home.adapter.RoomsViewPagerAdapter

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRoomsViewPager()
        binding.fabAddRoom.setOnClickListener {
            navigateToRoomCreation()
        }
    }

    private fun navigateToRoomCreation() {
        startActivity(Intent(this, RoomCreationActivity::class.java))
    }

    override fun initViewModel() = ViewModelProvider(this)[HomeViewModel::class.java]
    override fun getLayoutId() = R.layout.activity_home

    private fun initRoomsViewPager() {
        val adapter = RoomsViewPagerAdapter(this)
        binding.roomsViewPager.adapter = adapter
        TabLayoutMediator(binding.roomsTabLayout, binding.roomsViewPager) { tab, position ->
            val tabTitles =
                resources?.getStringArray(R.array.rooms_fragments_titles) ?: emptyArray()
            tab.text = tabTitles[position]
        }.attach()
    }


}


