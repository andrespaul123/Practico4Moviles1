package com.example.practico4m1.models

data class Correo(
    val id:Int,
    val email:String,
    val persona_id:Int,
    val label : String,

)
typealias Correos = ArrayList<Correo>