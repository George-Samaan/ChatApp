package com.route.chatapp.ui.auth.fragments.login

import com.route.chatapp.database.User

sealed class LoginViewEvent {


    object NavigateToRegister : LoginViewEvent()

    data class NavigateToHome(val user: User) : LoginViewEvent()
}