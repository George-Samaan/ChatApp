package com.route.chatapp.ui.createroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.chatapp.databinding.ActivityRoomCreationBinding


class RoomCreationActivity : AppCompatActivity() {
    private var _binding: ActivityRoomCreationBinding? = null
    private val binding: ActivityRoomCreationBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRoomCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}