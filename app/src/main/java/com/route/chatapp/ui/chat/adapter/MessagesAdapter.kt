package com.route.chatapp.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.route.chatapp.database.ChatMessage
import com.route.chatapp.databinding.ItemRecievedMessageBinding
import com.route.chatapp.databinding.ItemSentMessageBinding

class MessagesAdapter(private var messageList: MutableList<ChatMessage?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MessageType.SentMessage.value -> {
                val binding = ItemSentMessageBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
                return SentMessageViewHolder(binding)
            }

            else -> {
                val binding = ItemRecievedMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ReceivedMessageViewHolder(binding)

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val user = Firebase.auth.currentUser ?: throw Exception("UserNotFound")
        val senderId = messageList[position]?.senderId ?: ""
        return if (senderId == user.uid)
            MessageType.SentMessage.value
        else
            MessageType.ReceivedMessage.value
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position] ?: ChatMessage()
        when (holder) {
            is SentMessageViewHolder -> holder.bind(message)
            is ReceivedMessageViewHolder -> holder.bind(message)
        }
    }

    fun setMessagesList(list: List<ChatMessage?>) {
        messageList = list.toMutableList()
//        notifyItemRangeInserted(0 ,list.size)
        notifyDataSetChanged()
    }

    fun addMessages(list: ChatMessage?) {
        val oldSize = messageList.size
        messageList.add(list)
//        notifyItemRangeInserted(oldSize - 1 , 1)
        notifyDataSetChanged()
    }

    class SentMessageViewHolder(val binding: ItemSentMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.message = message
        }
    }

    class ReceivedMessageViewHolder(val binding: ItemRecievedMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.message = message
        }
    }
}