package com.route.chatapp.ui.home.fragments.myrooms

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.route.chatapp.base.BaseViewModel
import com.route.chatapp.database.Room
import com.route.chatapp.ui.Constants

class MyRoomsViewModel : BaseViewModel() {

    val myRooms = MutableLiveData<List<Room>>()
    fun checkUserInRoom(room: Room): Boolean {
        val user = Firebase.auth.currentUser ?: throw Exception("user Not Found")
        return room.membersIdList!!.contains(user.uid)
    }

    fun fetchRooms() {
        val user = Firebase.auth.currentUser ?: return
        Firebase.firestore.collection(Constants.ROOMS_COLLECTION)
            .whereEqualTo(Room.ownerIdField, user.uid)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    myRooms.value = task.result.toObjects(Room::class.java)
                } else {

                }

            }
    }

}
