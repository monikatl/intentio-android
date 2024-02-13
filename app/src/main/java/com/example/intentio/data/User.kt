package com.example.intentio.data

import android.provider.ContactsContract.CommonDataKinds.Email

class User (
    val type: UserType,
    val firstName: String,
    val name: String,
    val address: String,
    val email: String,
    val number: String
    ) : ICreateIntentionOrder {
    override fun createIntentionOrder() {
        TODO("Not yet implemented")
    }

}