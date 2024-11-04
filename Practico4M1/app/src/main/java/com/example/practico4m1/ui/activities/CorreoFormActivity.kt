package com.example.practico4m1.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practico4m1.databinding.ActivityEmailFormBinding

import com.example.practico4m1.models.Correo
import com.example.practico4m1.ui.viewmodels.CorreoViewModel

class CorreoFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailFormBinding
    private val viewModel: CorreoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.correoCreate.observe(this) {
            Toast.makeText(this, "Correo creado", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Error al crear el correo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupEventListeners() {
        binding.btnGuardarCorreo.setOnClickListener {
            val correo = Correo(
                id = 0,
                email = binding.txtEmailF.text.toString(),
                persona_id = intent.getIntExtra(PERSONA_ID_PARAM, 0),
                label = binding.txtEtiquetaCorreo.text.toString()
            )
            viewModel.createCorreo(correo)
        }
    }

    companion object {
        private const val PERSONA_ID_PARAM = "persona_id"

        fun nuevo(context: Context, id: Int): Intent {
            return Intent(context, CorreoFormActivity::class.java).apply {
                putExtra(PERSONA_ID_PARAM, id)
            }
        }
    }
}
