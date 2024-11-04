package com.example.practico4m1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4m1.models.Contacto
import com.example.practico4m1.repositories.ContactoRepository

class ContactoCreateViewmodel: ViewModel() {
    val contacto = MutableLiveData<Contacto>()
    private val _contactoCreate = MutableLiveData<Contacto>()
    val contactoCreate : LiveData<Contacto> = _contactoCreate

    private val _contactoUpdate = MutableLiveData<Contacto>()
    val contactoUpdate : LiveData<Contacto> = _contactoUpdate


    private val _error = MutableLiveData<Throwable>()
    val error : LiveData<Throwable> = _error

    fun createContacto(contacto: Contacto) {
        ContactoRepository.createContacto(contacto,
            onSuccess = {
                _contactoCreate.value = it
            }, onError = {
                _error.value = it
            }
        )
    }

    fun loadContacto(id: Int) {
        ContactoRepository.getContactotById(id,
            onSuccess = {
                contacto.value = it
            }, onError = {
                _error.value = it
            }
        )

    }
    fun updateContacto(id: Int, contacto: Contacto) {
        ContactoRepository.updateContacto(id,contacto,
            onSuccess = {
                _contactoUpdate.value = it
            }, onError = {
                _error.value = it
            }
        )
    }
}