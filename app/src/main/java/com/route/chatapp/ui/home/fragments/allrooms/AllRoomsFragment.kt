package com.route.chatapp.ui.home.fragments.allrooms

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseFragment
import com.route.chatapp.databinding.FragmentAllRoomsBinding
import com.route.chatapp.ui.Constants
import com.route.chatapp.ui.chat.ChatActivity
import com.route.chatapp.ui.home.adapter.RoomsRecyclerViewAdapter
import com.route.chatapp.ui.roomdetails.RoomDetailsActivity


class AllRoomsFragment : BaseFragment<FragmentAllRoomsBinding, AllRoomsViewModel>() {
    override fun initViewModel(): AllRoomsViewModel {
        return ViewModelProvider(this)[AllRoomsViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_all_rooms
    }

    private lateinit var adapter: RoomsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeLiceData()
        viewModel.fetchRooms()
    }

    private fun observeLiceData() {
        viewModel.allRooms.observe(viewLifecycleOwner) { roomsList ->
            adapter.updateRooms(roomsList)
        }
    }

    private fun initRecyclerView() {
        adapter = RoomsRecyclerViewAdapter(emptyList())
        adapter.setOnRoomClickListener { room ->
            if (viewModel.checkUserInRoom(room)) {
                startActivity(
                    Intent(requireActivity(), ChatActivity::class.java)
                        .putExtra(Constants.PASSED_ROOM, room)
                )
            } else {
                startActivity(
                    Intent(requireActivity(), RoomDetailsActivity::class.java)
                        .putExtra(Constants.PASSED_ROOM, room)
                )
            }

        }
        binding.roomsRv.adapter = adapter
    }

}