package com.route.chatapp.ui.chat

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.firestore
import com.route.chatapp.base.BaseViewModel
import com.route.chatapp.database.ChatMessage
import com.route.chatapp.database.Room
import com.route.chatapp.ui.Constants


class ChatViewModel : BaseViewModel() {

    val messageContent = MutableLiveData<String>()
    val messagesState = MutableLiveData<MessageState>()


    fun sendMessage(room: Room) {
        val user = Firebase.auth.currentUser ?: return

        val messages = ChatMessage(
            content = messageContent.value,
            senderId = user.uid,
            timeStamp = Timestamp.now()
        )

        Firebase.firestore.collection(Constants.ROOMS_COLLECTION)
            .document(room.uid!!)
            .collection(Constants.MESSAGES_COLLECTION)
            .add(messages)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {

                }
            }
    }

    fun listenToMessagesChange(room: Room) {

        Firebase.firestore
            .collection(Constants.ROOMS_COLLECTION)
            .document(room.uid!!)
            .collection(Constants.MESSAGES_COLLECTION)
            .addSnapshotListener { value, error ->
                for (document in value?.documentChanges!!) {
                    when (document.type) {
                        DocumentChange.Type.ADDED -> {
                            messagesState.value = MessageState.MessagesAdded(
                                document.document.toObject(
                                    ChatMessage::class.java
                                )
                            )
                        }

                        DocumentChange.Type.MODIFIED -> {

                        }

                        DocumentChange.Type.REMOVED -> {

                        }

                    }
                }
            }
    }

    fun fetchMessages(room: Room) {
        Firebase.firestore
            .collection(Constants.ROOMS_COLLECTION)
            .document(room.uid!!)
            .collection(Constants.MESSAGES_COLLECTION)
            .orderBy(ChatMessage.timestampField)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    messagesState.value = MessageState.MessagesListFetched(
                        it.result.toObjects(ChatMessage::class.java)
                    )
                }
            }
    }

}
