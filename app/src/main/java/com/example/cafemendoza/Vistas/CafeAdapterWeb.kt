package com.example.cafemendoza.Vistas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cafemendoza.Modelos.WebCafe
import com.example.cafemendoza.R

class CafeAdapterWeb(val contexto: Context, val lista:List<WebCafe>): RecyclerView.Adapter<CafeAdapterWeb.ViewHolderWeb>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderWeb {
        val view= LayoutInflater.from(contexto).inflate(R.layout.item_cafe_web,parent,false)
        return ViewHolderWeb(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolderWeb,
        position: Int
    ) {
        val cafe =lista[position]
        holder.nombreweb.text=cafe.nombre
        holder.puntuacion.text=cafe.puntuacion_sca.toString()
        //cargamos la imagen usando glide
        Glide.with(contexto)
            .load("https://cafemendoza.grupoahost.com/img/"+cafe.nombre_imagen)
            .into(holder.imagen)

        //creamos el listener para ver la vista detalle del cafe dando clik a la imagen
        holder.imagen.setOnClickListener {
            verDetalle(cafe)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun verDetalle(cafe: WebCafe){
        //llamada al activity detalle
        val intent= Intent(contexto,DetalleWebActivity::class.java)
            .apply {
                putExtra("nombre",cafe.nombre)
                putExtra("puntuacion","${cafe.puntuacion_sca} puntos SCA".toString())
                putExtra("imagen",cafe.nombre_imagen)
                putExtra("proceso",cafe.proceso)
                putExtra("productor",cafe.productor_nombre)
                putExtra("altura"," ${cafe.altura_siembra} msnm".toString())
                putExtra("variedad",cafe.variedad_de_cafe)
                putExtra("notas",cafe.notas_de_cata)
            }
        contexto.startActivity(intent)
    }

    class ViewHolderWeb(control: View): RecyclerView.ViewHolder(control){
        val nombreweb: TextView=control.findViewById(R.id.txvNombreweb)
        val puntuacion: TextView=control.findViewById(R.id.txvPuntuacionweb)
        val imagen: ImageView=control.findViewById(R.id.imgfotoweb)
    }
}