package com.example.intentio.data

data class HolyMass(
    var date: String,
    var hour: String,
    var priests: List<Priest> = emptyList(),
    val intentions: List<Intent> = emptyList(),
    var freeIntentCounter: Int = 1) {


    fun addFreeIntent() {
        freeIntentCounter++
    }

    fun assignPriest(priest: Priest) {
        priests.toMutableList().add(priest)
    }

    fun addPriest(priest: Priest) {
        if(priests.isNotEmpty()) freeIntentCounter++
        assignPriest(priest)
    }

    fun assignIntent(intention: Intent) {
        val isSuccess = intentions.toMutableList().add(intention)
        if (isSuccess) freeIntentCounter--
    }
}