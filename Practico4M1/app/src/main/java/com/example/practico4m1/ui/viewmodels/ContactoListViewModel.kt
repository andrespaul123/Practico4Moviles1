package com.example.practico4m1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4m1.models.Contactos
import com.example.practico4m1.repositories.ContactoRepository

class ContactoListViewModel : ViewModel() {
    private val _contactoList = MutableLiveData<Contactos>().apply {
        value = arrayListOf()
    }
    val contactoList : LiveData<Contactos> = _contactoList

    fun getContactoList() {
        ContactoRepository.getContactotList(
            onSuccess = {
                _contactoList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )
    }



}