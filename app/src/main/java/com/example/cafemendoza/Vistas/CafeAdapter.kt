package com.example.cafemendoza.Vistas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafemendoza.Modelos.Cafe
import com.example.cafemendoza.R

class CafeAdapter(private val cafes: List<Cafe>) : RecyclerView.Adapter<CafeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cafe,parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cafe = cafes[position]
        holder.txvNombre.text = cafe.nombre
        holder.txvPrecio.text = "$${cafe.precio}"
    }

    override fun getItemCount(): Int {
        return cafes.size
    }

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val txvNombre= ItemView.findViewById<TextView>(R.id.txvNombre)
        val txvPrecio= ItemView.findViewById<TextView>(R.id.txvPrecio)
        val imgfoto=ItemView.findViewById<ImageView>(R.id.imgfoto)
    }
}