package com.example.intentio.data

open class Sacrament {

}

sealed class Marriage : Sacrament() {

}

sealed class HolyOrders : Sacrament() {

}

sealed class AnointingTheSick : Sacrament() {

}

sealed class Reconciliation : Sacrament() {

}

sealed class Eucharist : Sacrament() {

}

sealed class Baptism : Sacrament() {

}

sealed class Confirmation : Sacrament() {

}