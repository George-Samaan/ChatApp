package com.route.chatapp.ui.home.fragments.allrooms

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.route.chatapp.base.BaseViewModel
import com.route.chatapp.database.Room
import com.route.chatapp.ui.Constants

class AllRoomsViewModel : BaseViewModel() {

    val allRooms = MutableLiveData<List<Room>>()

    fun fetchRooms() {
        Firebase.firestore.collection(Constants.ROOMS_COLLECTION)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    allRooms.value = task.result.toObjects(Room::class.java)
                } else {

                }
            }
    }

    fun checkUserInRoom(room: Room): Boolean {
        val user = Firebase.auth.currentUser ?: throw Exception("User Not Found")
        return room.membersIdList!!.contains(user.uid)
    }

}
