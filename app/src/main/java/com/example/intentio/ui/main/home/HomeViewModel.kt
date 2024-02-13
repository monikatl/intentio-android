package com.example.intentio.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intentio.data.Intent
import com.example.intentio.ui.main.Repository

class HomeViewModel : ViewModel() {

    private val _intents = MutableLiveData<List<Intent>>().apply {
        value = Repository.intents
    }
    val intents: LiveData<List<Intent>> = _intents
}