package com.route.chatapp.ui.auth.fragments.register

import com.route.chatapp.database.User

sealed class RegisterViewEvents {
    data class NavigateToHome
        (val user: User) : RegisterViewEvents()
}