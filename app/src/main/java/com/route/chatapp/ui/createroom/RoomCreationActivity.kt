package com.route.chatapp.ui.createroom

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseActivity
import com.route.chatapp.database.Room
import com.route.chatapp.databinding.ActivityRoomCreationBinding
import com.route.chatapp.ui.Constants
import com.route.chatapp.ui.chat.ChatActivity

class RoomCreationActivity : BaseActivity
<ActivityRoomCreationBinding, RoomCreationViewModel>() {
    override fun initViewModel() = ViewModelProvider(this)[RoomCreationViewModel::class.java]
    override fun getLayoutId() = R.layout.activity_room_creation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBinding()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.events.observe(this) { event ->
            when (event) {
                is RoomCreationEvents.RoomCreated -> navigateToChat(event.room)
            }

        }
    }

    private fun navigateToChat(room: Room) {
        startActivity(
            Intent(this, ChatActivity::class.java)
                .putExtra(Constants.PASSED_ROOM, room)
        )
        finish()
    }

    private fun initBinding() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }


}