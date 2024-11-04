package com.example.practico4m1.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.practico4m1.R
import com.example.practico4m1.databinding.ActivityMainBinding
import com.example.practico4m1.ui.adapters.ContactoListAdapter
import com.example.practico4m1.ui.viewmodels.ContactoListViewModel
import com.example.practico4m1.ui.viewmodels.MainViewModel

class MainActivity :  AppCompatActivity() {
private lateinit var binding : ActivityMainBinding
private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadContactos()
        setupViewModelObservers()
        setupEventListeners()


    }

    private fun setupViewModelObservers() {
        viewModel.name.observe(this) {
            binding.lblNombre.text = it
        }
        viewModel.lastName.observe(this) {
            binding.lblApellido.text = it
        }
        viewModel.company.observe(this) {
            binding.lblCompania.text = it
        }
        viewModel.address.observe(this) {
            binding.lblDireccion.text = it
        }
        viewModel.city.observe(this) {
            binding.lblCiudad.text = it
        }
        viewModel.state.observe(this) {
            binding.lblEstado.text = it
        }

        viewModel.phones.observe(this) { phones ->
            if (phones.isNotEmpty()) {
                binding.lblTelefono.text = phones[0].number
            } else {
                binding.lblTelefono.text = "No hay teléfonos disponibles"
            }
        }
        viewModel.emails.observe(this) { emails ->
            if (emails.isNotEmpty()) {
                binding.lblCorreo.text =
                    emails[0].email
            } else {
                binding.lblCorreo.text = "No hay correos disponibles"
            }
        }
        // Observa la URL de la imagen y usa Glide para cargarla
        viewModel.profile_picture.observe(this) { url ->
            Glide.with(this)
                .load(url)          
                .into(binding.lblImagenContacto)
        }
    }

    private fun loadContactos() {
        val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
        if(personaId == 0)
            return
            viewModel.getContactotById(personaId)

    }

    // eliminar contacto
    private fun setupEventListeners() {
        binding.btnEliminarContacto.setOnClickListener {
            val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
            if(personaId == 0) return@setOnClickListener
            viewModel.deleteContactotById(personaId)
            finish()
            startActivity(Intent(this, ContactoListActivity::class.java))


        }
        binding.btnEditarContacto.setOnClickListener {
            val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
            val intent = ContactoFormActivity.nuevo(this, personaId)
            startActivity(intent)
        }
    }

    companion object {
        const val PERSONA_ID_PARAM = "personaId"
        fun newIntent(context: Context, id: Int): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(PERSONA_ID_PARAM, id)
            }
        }
    }
}