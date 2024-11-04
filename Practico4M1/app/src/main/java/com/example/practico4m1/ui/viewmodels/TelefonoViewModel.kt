package com.example.practico4m1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4m1.models.Telefono
import com.example.practico4m1.repositories.TelefonoRepository

class TelefonoViewModel: ViewModel() {
    val telefono = MutableLiveData<Telefono>()
    private val _telefonoCreate = MutableLiveData<Telefono>()
    val telefonoCreate: LiveData<Telefono> = _telefonoCreate

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun createTelefono(telefono: Telefono) {
        TelefonoRepository.createTelefono(telefono,
            onSuccess = {
                _telefonoCreate.value = it
            }, onError = {
                _error.value = it
            }
        )
    }
    fun loadTelefono(id: Int) {
        TelefonoRepository.getTelefonoById(id,
            onSuccess = {
                telefono.value = it
            }, onError = {
}
        )
    }
    fun updateTelefono(id: Int, telefono: Telefono) {
        TelefonoRepository.updateTelefono(id, telefono,
            onSuccess = {
                _telefonoCreate.value = it
            }, onError = {
                _error.value = it
            }
        )
    }
}