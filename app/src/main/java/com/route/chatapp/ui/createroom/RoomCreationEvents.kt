package com.route.chatapp.ui.createroom

import com.route.chatapp.database.Room

sealed class RoomCreationEvents {

    data class RoomCreated(val room: Room) : RoomCreationEvents()
}