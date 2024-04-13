package com.route.chatapp.ui.home.fragments.myrooms

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseFragment
import com.route.chatapp.databinding.FragmentMyRoomsBinding
import com.route.chatapp.ui.Constants
import com.route.chatapp.ui.chat.ChatActivity
import com.route.chatapp.ui.home.adapter.RoomsRecyclerViewAdapter
import com.route.chatapp.ui.roomdetails.RoomDetailsActivity


class MyRoomsFragment : BaseFragment<FragmentMyRoomsBinding, MyRoomsViewModel>() {
    override fun initViewModel() = ViewModelProvider(this)[MyRoomsViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_my_rooms

    private lateinit var adapter: RoomsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeLiveData()
        viewModel.fetchRooms()
    }

    private fun observeLiveData() {
        viewModel.myRooms.observe(viewLifecycleOwner) { roomsList ->
            adapter.updateRooms(roomsList)
        }
    }

    private fun initRecyclerView() {
        adapter = RoomsRecyclerViewAdapter(emptyList())
        adapter.setOnRoomClickListener { room ->
            if (viewModel.checkUserInRoom(room)) {
                startActivity(
                    Intent(requireActivity(), ChatActivity::class.java)
                        .putExtra(
                            Constants.PASSED_ROOM, room
                        )
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