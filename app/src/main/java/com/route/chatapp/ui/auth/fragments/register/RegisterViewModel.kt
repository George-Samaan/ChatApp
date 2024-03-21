package com.route.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.route.chatapp.base.BaseViewModel
import com.route.chatapp.database.MyDatabase
import com.route.chatapp.database.User
import com.route.chatapp.ui.ViewMessage

class RegisterViewModel : BaseViewModel() {

    val userNameLiveData = MutableLiveData<String>()
    val userNameError = MutableLiveData<String?>()
    val emailLiveData = MutableLiveData<String>()
    val emailError = MutableLiveData<String?>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()
    val confirmPasswordLiveData = MutableLiveData<String>()
    val confirmPasswordError = MutableLiveData<String?>()
    val isRegistering = MutableLiveData(false)
    private val authService = Firebase.auth

    val events = MutableLiveData<RegisterViewEvents>()


    fun register() {
        if (isRegistering.value == true) return
        if (!validateInputs()) return
        isRegistering.value = true
        authService.createUserWithEmailAndPassword(
            emailLiveData.value!!,
            passwordLiveData.value!!
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result.user
                registerUserInDataBase(user!!.uid)
            } else {
                isRegistering.value = false
                viewMessage.value = ViewMessage(
                    message = task.exception?.localizedMessage ?: "Something Went Wrong"
                )
            }
        }
    }

    private fun registerUserInDataBase(uid: String) {
        val user = User(
            uid,
            userNameLiveData.value!!,
            emailLiveData.value!!
        )
        MyDatabase.createUser(user) { task ->
            isRegistering.value = false
            if (task.isSuccessful) {
                events.postValue(
                    RegisterViewEvents.NavigateToHome(user)
                )
            } else {
                viewMessage.value = ViewMessage(
                    message = task.exception?.localizedMessage ?: "Something Went Wrong",
                    posActionName = "Ok"
                )
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        if (userNameLiveData.value.isNullOrBlank()) {
            userNameError.value = "Please Enter UserName"
            isValid = false
        } else {
            userNameError.value = null
        }
        if (emailLiveData.value.isNullOrBlank()) {
            emailError.value = "Enter Your Email"
            isValid = false
        } else {
            emailError.value = null
        }
        if (passwordLiveData.value.isNullOrBlank()) {
            passwordError.value = "Please Enter Password"
            isValid = false
        } else if (passwordLiveData.value!!.length < 6) {
            passwordError.value = "Password Must Be More Than 6 chars"
        } else {
            passwordError.value = null
        }

        if (confirmPasswordLiveData.value.isNullOrBlank()) {
            confirmPasswordError.value = "Check Your password"
            isValid = false
        } else if (confirmPasswordLiveData.value != passwordLiveData.value) {
            confirmPasswordError.value = "password Doesn't Match"
            isValid = false
        } else {
            confirmPasswordError.value = null
        }

        return isValid
    }
}