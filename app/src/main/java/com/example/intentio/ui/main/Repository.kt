package com.example.intentio.ui.main

import com.example.intentio.data.HolyMass
import com.example.intentio.data.Intent
import com.example.intentio.data.User
import com.example.intentio.data.UserType

// get data from Spring app by REST API
object Repository {
    val user: User = User(UserType.FAITHFUL, "Monika", "Baszczyk", "Lipowa 17/19", "monikatl@interia.pl", "513833744")
    val intents = mutableListOf<Intent>()
    val masses: List<HolyMass> = listOf(
        HolyMass(date = "13.02.2024", hour = "7:00", freeIntentCounter = 0),
        HolyMass("13.02.2024", "18:00"),
        HolyMass("14.02.2024", "7:00"),
        HolyMass("14.02.2024", "18:00", freeIntentCounter = 0),
        HolyMass("15.02.2024", "7:00"),
        HolyMass("15.02.2024", "18:00")
    )
    val settings: String = ""
}