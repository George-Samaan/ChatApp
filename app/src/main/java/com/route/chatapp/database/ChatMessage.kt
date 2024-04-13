package com.route.chatapp.database

import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class ChatMessage(
    val uid: String? = null,
    val content: String? = null,
    val timeStamp: Timestamp? = null,
    val senderId: String? = null,
) {

    //    companion object{
//        const val uidMessageField = "uid"
//    }
    fun getFormattedTime(): String {
        val date = Date(timeStamp?.toDate()?.time!!)

        val timeFormatter = SimpleDateFormat("hh:mm a", Locale.US)  // update
        return timeFormatter.format(date)
    }

    fun getUserName(): String = Firebase.auth.currentUser?.displayName ?: "Not Found"

    companion object {
        const val timestampField = "timeStamp"
    }
}
