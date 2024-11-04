package com.example.practico4m1.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practico4m1.R
import com.example.practico4m1.databinding.ActivityContactoFormBinding
import com.example.practico4m1.models.Contacto
import com.example.practico4m1.ui.viewmodels.ContactoCreateViewmodel

class ContactoFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactoFormBinding
    private val viewModel: ContactoCreateViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactoFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.getBooleanExtra(PERSONA_EDIT_PARAM, false)) {
            val contactoId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
            if (contactoId != 0) {
                viewModel.loadContacto(contactoId)

            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

    }

    private fun setupViewModelObservers() {
        viewModel.contacto.observe(this) {
            binding.txtContactoNombre.editText?.setText(it.name)
            binding.txtContactoApellido.editText?.setText(it.last_name)
            binding.txtContactoCompania.editText?.setText(it.company)
            binding.txtContactoDireccion.editText?.setText(it.address)
            binding.txtContactoCiudad.editText?.setText(it.city)
            binding.txtContactoEstado.editText?.setText(it.state)
        }
        viewModel.contactoCreate.observe(this) {
            Toast.makeText(this, "Contacto creado", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.contactoUpdate.observe(this) {
            Toast.makeText(this, "Contacto actualizado", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Error al crear el contacto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupEventListeners() {

        binding.btnGuardarContacto.setOnClickListener {
            val contacto = Contacto(
                id = 0,
                name = binding.txtContactoNombre.editText?.text.toString(),
                last_name = binding.txtContactoApellido.editText?.text.toString(),
                company = binding.txtContactoCompania.editText?.text.toString(),
                address = binding.txtContactoDireccion.editText?.text.toString(),
                city = binding.txtContactoCiudad.editText?.text.toString(),
                state = binding.txtContactoEstado.editText?.text.toString()
            )
            if (intent.getBooleanExtra(PERSONA_EDIT_PARAM, false)) {
                val contactoId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
                if (contactoId != 0) {
                    viewModel.updateContacto(contactoId, contacto)
                }
            } else {
                viewModel.createContacto(contacto)
            }
        }
    }

    companion object {
        private const val PERSONA_ID_PARAM = "persona_id"
        private const val PERSONA_EDIT_PARAM = "persona_edit"

        fun nuevo(context: Context, id: Int): Intent {
            return Intent(context, ContactoFormActivity::class.java).apply {
                putExtra(PERSONA_ID_PARAM, id)
                putExtra(PERSONA_EDIT_PARAM, true)
            }
        }
    }
}


