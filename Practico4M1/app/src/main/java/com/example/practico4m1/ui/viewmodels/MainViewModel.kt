package com.example.practico4m1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4m1.models.Correo
import com.example.practico4m1.models.Telefono
import com.example.practico4m1.repositories.ContactoRepository

class MainViewModel : ViewModel() {
    private val _name = MutableLiveData<String>().apply {
        value = ""
    }
    val name: LiveData<String> = _name
    private val _lastName = MutableLiveData<String>().apply {
        value = ""
    }
    val lastName: LiveData<String> = _lastName
    private val _company = MutableLiveData<String>().apply {
        value = ""
    }
    val company: LiveData<String> = _company
    private val _address = MutableLiveData<String>().apply {
        value = ""
    }
    val address: LiveData<String> = _address
    private val _city = MutableLiveData<String>().apply {
        value = ""
    }
    val city: LiveData<String> = _city

    private val _state = MutableLiveData<String>().apply {
        value = ""
    }
    val state: LiveData<String> = _state

    private val _profilePictureUrl = MutableLiveData<String>().apply { value = "" }
    val profile_picture: LiveData<String> = _profilePictureUrl

    private val _phones = MutableLiveData<List<Telefono>>().apply {
        value = emptyList()
    }
    val phones: LiveData<List<Telefono>> = _phones

    private val _emails = MutableLiveData<List<Correo>>().apply {
        value = emptyList()
    }
    val emails: LiveData<List<Correo>> = _emails



    fun getContactotById(id: Int) {
        ContactoRepository.getContactotById(id,
            onSuccess = {
                _name.value = it.name
                _lastName.value = it.last_name
                _company.value = it.company
                _address.value = it.address
                _city.value = it.city
                _state.value = it.state
                _profilePictureUrl.value= it.profile_picture
                _phones.value = it.phones
                _emails.value = it.emails



                println("Post: $it")
            }, onError = {
                println("Error: $it")
            })
    }

    fun deleteContactotById(id: Int) {
        ContactoRepository.deleteContactById(id,
            onSuccess = {
                println("Contacto eliminado")
            }, onError = {
                println("Error: $it")
            })
    }
}