package com.route.chatapp.database

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore

object MyDatabase {

    private const val usersCollections = "users"
    fun createUser(user: User, onComplete: OnCompleteListener<Void>) {
        Firebase.firestore
            .collection(usersCollections)
            .document(user.uid ?: "")
            .set(user)
            .addOnCompleteListener(onComplete)
    }

    fun getUserFromDB(uid: String, onComplete: OnCompleteListener<DocumentSnapshot>) {
        Firebase.firestore.collection(usersCollections)
            .document(uid)
            .get()
            .addOnCompleteListener(onComplete)
    }
}