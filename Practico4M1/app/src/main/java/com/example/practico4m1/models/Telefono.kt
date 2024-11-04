package com.example.practico4m1.models

data class Telefono (
    val id : Int,
    val persona_id : Int,
    val number : String,
    val label : String



)
typealias Telefonos = ArrayList<Telefono>