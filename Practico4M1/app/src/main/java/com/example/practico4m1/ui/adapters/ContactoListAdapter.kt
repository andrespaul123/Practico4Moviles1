package com.example.practico4m1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practico4m1.R
import com.example.practico4m1.databinding.ContactoItemLayoutBinding
import com.example.practico4m1.models.Contacto
import com.example.practico4m1.models.Contactos

class ContactoListAdapter (private val listener:PersonaItemListener
) : RecyclerView.Adapter<ContactoListAdapter.ContactoItemViewHolder>() {
    private var contactoList: Contactos = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoItemViewHolder {
        val binding =
            ContactoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactoItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return contactoList.size
    }

    override fun onBindViewHolder(holder: ContactoItemViewHolder, position: Int) {
        holder.bind(contactoList[position],listener)
    }

    fun updateData(it: Contactos) {
        contactoList = it
        notifyDataSetChanged()

    }

    class ContactoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lblContactoItemNombre: TextView = itemView.findViewById(R.id.lblContactoItemNombre)
        fun bind(contacto: Contacto,listener: PersonaItemListener) {
            lblContactoItemNombre.text = contacto.name
            itemView.setOnClickListener {
                listener.onContactoItemClick(contacto)
            }
        }

    }
    interface PersonaItemListener {
        fun onContactoItemClick(contacto: Contacto)
    }

}