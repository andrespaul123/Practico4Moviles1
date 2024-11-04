package com.example.practico4m1.models

 data class Contacto (
     val id: Int,
     val name: String,
     val last_name: String,
     val company: String,
     val address: String,
     val city: String,
     val state: String,
     val profile_picture: String ="",
    // val email: Correo,
    // val phone: Telefono
     val phones: List<Telefono> = emptyList(),
     val emails: List<Correo> = emptyList()


 )
typealias Contactos = ArrayList<Contacto>