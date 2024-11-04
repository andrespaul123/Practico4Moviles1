package com.example.practico4m1.ui.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico4m1.R
import com.example.practico4m1.databinding.ActivityContactoListBinding
import com.example.practico4m1.models.Contacto
import com.example.practico4m1.ui.adapters.ContactoListAdapter
import com.example.practico4m1.ui.viewmodels.ContactoListViewModel

class ContactoListActivity : AppCompatActivity(), ContactoListAdapter.PersonaItemListener {
    lateinit var binding: ActivityContactoListBinding
    private val viewModel: ContactoListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        setupRecyclerView()
        setupEventListeners()
        //viewModel.getContactoList()
    }
    override fun onResume() {
        super.onResume()
       viewModel.getContactoList()
   }

    private fun setupEventListeners() {
        binding.btnCrearContacto.setOnClickListener {
            startActivity(Intent(this, ContactoFormActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        val adapter = ContactoListAdapter(this)
        binding.rvContactoList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@ContactoListActivity)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.contactoList.observe(this) {
            val adapter = binding.rvContactoList.adapter as ContactoListAdapter
            adapter.updateData(it)
        }

    }
    override fun onContactoItemClick(contacto: Contacto) {
        showActionDialog(contacto)
    }

    private fun showActionDialog(contacto: Contacto) {
        val options = arrayOf("Ver Detalle", "Agregar Teléfono","Agregar Correo")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona una acción")
        builder.setItems(options) { dialog: DialogInterface, which: Int ->
            when (which) {
                0 -> {
                    // Ir a la actividad de detalle del contacto
                    val intent = MainActivity.newIntent(this, contacto.id)
                    startActivity(intent)
                }
                1 -> {
                    // Ir al formulario de teléfono
                    val intent = TelefonoFormActivity.nuevo(this, contacto.id)
                    startActivity(intent)
                }
                2 -> {
                    // Ir al formulario de correo
                    val intent = CorreoFormActivity.nuevo(this, contacto.id)
                    startActivity(intent)
                }
            }
        }
        builder.show()
    }
}