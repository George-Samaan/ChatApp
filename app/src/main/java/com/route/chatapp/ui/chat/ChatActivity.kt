package com.route.chatapp.ui.chat

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseActivity
import com.route.chatapp.database.ChatMessage
import com.route.chatapp.database.Room
import com.route.chatapp.databinding.ActivityChatBinding
import com.route.chatapp.ui.Constants
import com.route.chatapp.ui.chat.adapter.MessagesAdapter

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>() {


    private lateinit var room: Room
    private lateinit var adapter: MessagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        room = getPassedRoom()
        initBinding()
        initRecyclerView()
        viewModel.fetchMessages(room)
        viewModel.listenToMessagesChange(room)
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.messagesState.observe(this) { state ->
            when (state) {
                is MessageState.MessagesListFetched -> adapter.setMessagesList(state.messagesList)
                is MessageState.MessagesAdded -> adapter.addMessages(state.addedMessages)
                else -> {
                    Log.e("tt", "error")
                }
            }
        }
    }

    private fun initRecyclerView() {
        val emptyList = emptyList<ChatMessage?>()
        adapter = MessagesAdapter(emptyList.toMutableList())
        binding.messagesRv.adapter = adapter
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

}