package com.example.practico4m1.repositories

import com.example.practico4m1.api.JSONPlaceHolderService
import com.example.practico4m1.models.Contacto
import com.example.practico4m1.models.Contactos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ContactoRepository {
    fun getContactotList(
        onSuccess: (Contactos) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getContactotList().enqueue(object : Callback<Contactos> {
            override fun onResponse(call: Call<Contactos>, response: Response<Contactos>) {
                if (response.isSuccessful) {
                    val contacto = response.body()
                    println("Contactos recibidos: $contacto")
                    onSuccess(contacto!!)
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Contactos>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }


    fun getContactotById(id: Int, onSuccess: (Contacto) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getContactotById(id).enqueue(object : Callback<Contacto> {
            override fun onResponse(call: Call<Contacto>, response: Response<Contacto>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Contacto>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun createContacto(contacto: Contacto, onSuccess: (Contacto) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.createContacto(contacto).enqueue(object : Callback<Contacto> {
            override fun onResponse(call: Call<Contacto>, response: Response<Contacto>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Contacto>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun deleteContactById(id: Int, onSuccess: (Contacto) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.deleteContacto(id).enqueue(object : Callback<Contacto> {
            override fun onResponse(call: Call<Contacto>, response: Response<Contacto>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Contacto>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun updateContacto(id: Int, contacto: Contacto, onSuccess: (Contacto) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.updateContacto(id, contacto).enqueue(object : Callback<Contacto> {
            override fun onResponse(call: Call<Contacto>, response: Response<Contacto>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Contacto>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}