package com.route.chatapp.ui.createroom

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.route.chatapp.base.BaseViewModel
import com.route.chatapp.database.Room
import com.route.chatapp.ui.Constants
import com.route.chatapp.ui.ViewMessage

class RoomCreationViewModel : BaseViewModel() {

    val roomName = MutableLiveData<String>()
    val errorRoomName = MutableLiveData<String>()
    val roomCategory = MutableLiveData<String>()
    val errorRoomCategory = MutableLiveData<String>()
    val roomDescription = MutableLiveData<String>()
    val errorRoomDescription = MutableLiveData<String>()
    val events = MutableLiveData<RoomCreationEvents>()

    // wenta b create room heya betal3 uid automatic
    // w m3ak refrence 3la room f btgeeb mnha el uid el generated w t7oto fl feild
    fun createRoom() {
        if (!validateInputs()) return
        val user = Firebase.auth.currentUser ?: return
        val room = Room(
            title = roomName.value,
            category = roomCategory.value,
            description = roomDescription.value,
            ownerId = user.uid,
            membersIdList = listOf(user.uid),
        )

        Firebase.firestore
            .collection(Constants.ROOMS_COLLECTION)
            .add(room)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
//task.result.id == uid elly ta7t
                    updateRoomId(task.result.id, room)
//Log.d("ttt",task.result.id)
                } else {
                    viewMessage.value = ViewMessage(
                        message = task.exception?.localizedMessage ?: "SWR"
                    )
                }

            }
    }

    private fun validateInputs(): Boolean {
        val isValid = true

        return isValid
    }

    private fun updateRoomId(uid: String, room: Room) {
        Firebase.firestore
            .collection(Constants.ROOMS_COLLECTION)
            .document(uid)
            .update(Room.uidField, uid)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updatedRoom = room.copy(uid = uid)
                    events.value = RoomCreationEvents.RoomCreated(updatedRoom)
                } else {

                }
            }
    }

}
