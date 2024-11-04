package com.example.practico4m1.repositories

import com.example.practico4m1.api.JSONPlaceHolderService
import com.example.practico4m1.models.Telefono
import com.example.practico4m1.models.Telefonos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TelefonoRepository {
    fun getTelefonoList(
        onSuccess: (Telefonos) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getTelefonoList().enqueue(object : Callback<Telefonos> {
            override fun onResponse(call: Call<Telefonos>, response: Response<Telefonos>) {
                if (response.isSuccessful) {
                    val telefonos = response.body()
                    println("Teléfonos recibidos: $telefonos") // Agregado para debug
                    onSuccess(telefonos!!)
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Telefonos>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun getTelefonoById(id: Int, onSuccess: (Telefono) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getTelefonoById(id).enqueue(object : Callback<Telefono> {
            override fun onResponse(call: Call<Telefono>, response: Response<Telefono>) {
                if (response.isSuccessful) {
                    val telefono = response.body()
                    onSuccess(telefono!!)
                }
            }

            override fun onFailure(call: Call<Telefono>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun createTelefono(telefono: Telefono, onSuccess: (Telefono) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.createTelefono(telefono).enqueue(object : Callback<Telefono> {
            override fun onResponse(call: Call<Telefono>, response: Response<Telefono>) {
                if (response.isSuccessful) {
                    val newTelefono = response.body()
                    onSuccess(newTelefono!!)
                }
            }

            override fun onFailure(call: Call<Telefono>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun deleteTelefonoById(id: Int, onSuccess: (Telefono) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.deleteTelefono(id).enqueue(object : Callback<Telefono> {
            override fun onResponse(call: Call<Telefono>, response: Response<Telefono>) {
                if (response.isSuccessful) {
                    val telefono = response.body()
                    onSuccess(telefono!!)
                }
            }

            override fun onFailure(call: Call<Telefono>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun updateTelefono(id: Int, telefono: Telefono, onSuccess: (Telefono) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.updateTelefono(id, telefono).enqueue(object : Callback<Telefono> {
            override fun onResponse(call: Call<Telefono>, response: Response<Telefono>) {
                if (response.isSuccessful) {
                    val updatedTelefono = response.body()
                    onSuccess(updatedTelefono!!)
                }
            }

            override fun onFailure(call: Call<Telefono>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}



