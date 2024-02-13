package com.example.intentio.data

data class Intent(val type: IntentType, var name: String, var date: String, var orderer: User, var priest: Priest) {

}