package com.example.practico4m1.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practico4m1.databinding.ActivityTelefonoFormBinding
import com.example.practico4m1.models.Telefono
import com.example.practico4m1.ui.activities.ContactoFormActivity.Companion

import com.example.practico4m1.ui.viewmodels.TelefonoViewModel

class TelefonoFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTelefonoFormBinding
    private val viewModel: TelefonoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelefonoFormBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
        Toast.makeText(this, "Persona ID recibido: $personaId", Toast.LENGTH_SHORT).show()
        Log.d("TelefonoFormActivity", "Persona ID recibido: $personaId")
        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.telefonoCreate.observe(this) {
            Toast.makeText(this, "Teléfono creado", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Error al crear el teléfono", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupEventListeners() {
        binding.btnGuardarTelefono.setOnClickListener {

            val telefono = Telefono(
                id = 0,
                persona_id = intent.getIntExtra(PERSONA_ID_PARAM, 0),
                number = binding.txtTelefonoNumero.editText?.text.toString(),
                label = binding.txtTelefonoEtiqueta.editText?.text.toString()
            )
            viewModel.createTelefono(telefono)
        }
    }

    companion object {
        private const val PERSONA_ID_PARAM = "persona_id"

        fun nuevo(context: Context, id: Int): Intent {
            return Intent(context, TelefonoFormActivity::class.java).apply {
                putExtra(PERSONA_ID_PARAM, id)
            }
        }
    }
}
