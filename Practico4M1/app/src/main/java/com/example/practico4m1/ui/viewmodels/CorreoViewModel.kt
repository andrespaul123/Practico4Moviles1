package com.example.practico4m1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4m1.models.Correo
import com.example.practico4m1.repositories.CorreoRepository

class CorreoViewModel : ViewModel() {
    private val _correoCreate = MutableLiveData<Correo>()
    val correoCreate: LiveData<Correo> = _correoCreate

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun createCorreo(correo: Correo) {
        CorreoRepository.createCorreo(correo,
            onSuccess = {
                _correoCreate.value = it
            }, onError = {
                _error.value = it
            }
        )
    }
}