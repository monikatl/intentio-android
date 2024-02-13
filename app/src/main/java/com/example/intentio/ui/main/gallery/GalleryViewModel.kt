package com.example.intentio.ui.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intentio.data.User
import com.example.intentio.ui.main.Repository

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    private val _user = MutableLiveData<User>().apply {
        value = Repository.user
    }
    val user: LiveData<User> = _user
}