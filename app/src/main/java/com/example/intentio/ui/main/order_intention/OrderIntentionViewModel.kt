package com.example.intentio.ui.main.order_intention

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intentio.data.HolyMass
import com.example.intentio.data.Intent
import com.example.intentio.ui.main.Repository

class OrderIntentionViewModel : ViewModel() {

    private val _intention = MutableLiveData<Intent>().apply {
        value = null
    }
    val intention: LiveData<Intent> = _intention

    private val _masses = MutableLiveData<List<HolyMass>>().apply {
        value = Repository.masses
    }
    val masses: LiveData<List<HolyMass>> = _masses


    fun orderIntention(new: Intent) {
        Repository.intents.add(new)

    }

}