package com.route.chatapp.ui.auth.fragments.login

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.route.chatapp.base.BaseViewModel
import com.route.chatapp.database.MyDatabase
import com.route.chatapp.database.User
import com.route.chatapp.ui.ViewMessage

class LoginViewModel : BaseViewModel() {

    val emailLiveData = MutableLiveData<String>()
    val emailError = MutableLiveData<String?>()
    val passwordLiveData = MutableLiveData<String>()
    val errorPassword = MutableLiveData<String?>()
    val isLoading = MutableLiveData(false)
    val authService = Firebase.auth
    val events = MutableLiveData<LoginViewEvent>()

    fun login() {
        if (isLoading.value == true) return
        if (!validateInputs()) return
        isLoading.value = true
        authService.signInWithEmailAndPassword(
            emailLiveData.value!!, passwordLiveData.value!!
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result.user
                getUserFromDataBase(user!!.uid)
            } else {
                isLoading.value = false
                viewMessage.value = ViewMessage(
                    message = task.exception?.localizedMessage ?: "Something Went Wrong"
                )
            }
        }
    }

    private fun getUserFromDataBase(uid: String) {
        MyDatabase.getUserFromDB(uid) { task ->
            isLoading.value = false
            val user = task.result.toObject(User::class.java)
            if (task.isSuccessful && user != null) {
                events.postValue(LoginViewEvent.NavigateToHome(user))
            } else {
                viewMessage.value = ViewMessage(
                    message = task.exception?.localizedMessage ?: "Something Went Wrong ",
                    posActionName = "Ok"
                )
            }

        }
    }

    fun onGoToRegisterClick() {
        events.postValue(LoginViewEvent.NavigateToRegister)
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        if (emailLiveData.value.isNullOrBlank()) {
            emailError.value = "Please Enter Email"
            isValid = false
        } else {
            emailError.value = null
        }
        if (passwordLiveData.value.isNullOrBlank()) {
            errorPassword.value = "Please Enter Your Password"
            isValid = false
        } else if (passwordLiveData.value?.length!! < 6) {
            errorPassword.value = "Password is WRONG "
            isValid = false
        } else {
            errorPassword.value = null
        }
        return isValid
    }

    fun checkUserLoggedIn() {
        val user = Firebase.auth.currentUser ?: return
        events.value = LoginViewEvent
            .NavigateToHome(
                User(
                    user.uid,
                    user.displayName,
                    user.email
                )
            )
    }


}