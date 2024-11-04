package com.example.practico4m1.repositories

import com.example.practico4m1.api.JSONPlaceHolderService
import com.example.practico4m1.models.Correo
import com.example.practico4m1.models.Correos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CorreoRepository {
    fun getCorreoList(
        onSuccess: (Correos) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getCorreoList().enqueue(object : Callback<Correos> {
            override fun onResponse(call: Call<Correos>, response: Response<Correos>) {
                if (response.isSuccessful) {
                    val correos = response.body()
                    onSuccess(correos ?: arrayListOf())
                } else {
                    onError(Exception("Error en la respuesta del servidor"))
                }
            }

            override fun onFailure(call: Call<Correos>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun createCorreo(correo: Correo, onSuccess: (Correo) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.createCorreo(correo).enqueue(object : Callback<Correo> {
            override fun onResponse(call: Call<Correo>, response: Response<Correo>) {
                if (response.isSuccessful) {
                    onSuccess(response.body()!!)
                } else {
                    onError(Exception("Error al crear el correo"))
                }
            }

            override fun onFailure(call: Call<Correo>, t: Throwable) {
                onError(t)
            }
        })
    }

    // Implementa los métodos para actualizar y eliminar correos si es necesario
}
