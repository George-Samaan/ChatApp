package com.route.chatapp.ui.chat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseActivity
import com.route.chatapp.database.Room
import com.route.chatapp.databinding.ActivityChatBinding
import com.route.chatapp.ui.Constants
import com.route.chatapp.ui.createroom.RoomCreationActivity

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>() {


    private lateinit var room: Room
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        room = getPassedRoom()
        initBinding()
        binding.sendMssgBtn.setOnClickListener {
            navigateToRoomCreation()
        }
    }

    private fun getPassedRoom(): Room {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.PASSED_ROOM, Room::class.java) ?: Room()
        } else {
            intent.getParcelableExtra(Constants.PASSED_ROOM) ?: Room()
        }
    }

    private fun initBinding() {
        binding.vm = viewModel
        binding.room = room
        binding.lifecycleOwner = this
    }

    override fun initViewModel() = ViewModelProvider(this)[ChatViewModel::class.java]

    override fun getLayoutId() = R.layout.activity_chat
    private fun navigateToRoomCreation() {
        startActivity(Intent(this, RoomCreationActivity::class.java))
        finish()
    }


}