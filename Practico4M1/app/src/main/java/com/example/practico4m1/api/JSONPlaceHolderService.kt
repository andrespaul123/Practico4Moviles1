package com.example.practico4m1.api

import com.example.practico4m1.models.Contacto
import com.example.practico4m1.models.Contactos
import com.example.practico4m1.models.Correo
import com.example.practico4m1.models.Correos
import com.example.practico4m1.models.Telefono
import com.example.practico4m1.models.Telefonos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JSONPlaceHolderService {


    @GET("api/personas/{id}")
    fun getContactotById(@Path("id")id: Int): Call<Contacto>


    @GET("api/personas")
    fun getContactotList(): Call<Contactos>

    @POST("api/personas")
    fun createContacto(@Body contacto: Contacto): Call<Contacto>

    @DELETE("api/personas/{id}")
    fun deleteContacto(@Path("id")id: Int): Call<Contacto>

    @PUT("api/personas/{id}")
    fun updateContacto(@Path("id")id: Int, @Body contacto: Contacto): Call<Contacto>


    //TELEFONO
    @GET("api/phones/{id}")
    fun getTelefonoById(@Path("id") id: Int): Call<Telefono>

    @GET("api/phones")
    fun getTelefonoList(): Call<Telefonos>

    @POST("api/phones")
    fun createTelefono(@Body telefono: Telefono): Call<Telefono>

    @DELETE("api/phones/{id}")
    fun deleteTelefono(@Path("id") id: Int): Call<Telefono>

    @PUT("api/phones/{id}")
    fun updateTelefono(@Path("id") id: Int, @Body phones: Telefono): Call<Telefono>


    //CORREO

    @GET("api/emails/{id}")
    fun getCorreoById(@Path("id") id: Int): Call<Correo>

    @GET("api/emails")
    fun getCorreoList(): Call<Correos>

    @POST("api/emails")
    fun createCorreo(@Body correo: Correo): Call<Correo>

    @DELETE("api/emails/{id}")
    fun deleteCorreo(@Path("id") id: Int): Call<Correo>

    @PUT("api/emails/{id}")
    fun updateCorreo(@Path("id") id: Int, @Body correo: Correo): Call<Correo>



}