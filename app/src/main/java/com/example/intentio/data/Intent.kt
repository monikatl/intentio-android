package com.example.intentio.data

data class Intent(
    val type: IntentType,
    var date: String,
    var hour: String,
    var kind: IntentionKind,
    var name: IntentionContent,
    var orderer: User,
    // if mass have assigned priest this is the priest if not null
    var priest: Priest? = null,
    var isAccepted: Boolean = false) {


}