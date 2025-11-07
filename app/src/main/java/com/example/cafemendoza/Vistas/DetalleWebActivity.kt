package com.example.cafemendoza.Vistas

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.cafemendoza.R

class DetalleWebActivity : AppCompatActivity() {

    //eclaro los witllets a utilizar
    private lateinit var nombre: TextView
    private lateinit var variedad: TextView
    private lateinit var productor: TextView
    private lateinit var puntuacion: TextView
    private lateinit var proceso:TextView
    private lateinit var notas:TextView
    private lateinit var imagen : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_web)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //inicializamos los witllets
        nombre = findViewById(R.id.txvNombreDetalle)
        variedad = findViewById(R.id.txvVariedadDetalle)
        productor = findViewById(R.id.txvproductorDetalle)
        puntuacion = findViewById(R.id.txvPuntuacionDetalle)
        proceso = findViewById(R.id.txvProcesoDetalle)
        notas = findViewById(R.id.txvNotasDetalle)
        imagen=findViewById(R.id.imgDetalle)

        //obtenemos los datos del intent
        val intent = intent
        val nombreDetalle = intent.getStringExtra("nombre")
        val puntuacionDetalle = intent.getStringExtra("puntuacion")
        val imagenDetalle = intent.getStringExtra("imagen")
        val procesoDetalle = intent.getStringExtra("proceso")
        val productorDetalle = intent.getStringExtra("productor")
        val variedadDetalle = intent.getStringExtra("variedad")
        val notasDetalle = intent.getStringExtra("notas")

        //cargamos la imagen con gidle
        Glide.with(this)
            .load("http://localhost/webserviceCafeMendoza/img/"+imagenDetalle)
            .into(imagen)
        //mostramos los datos en los witllets
        nombre.text = nombreDetalle
        variedad.text = variedadDetalle
        productor.text = productorDetalle
        puntuacion.text = puntuacionDetalle
        proceso.text = procesoDetalle
        notas.text = notasDetalle
    }
}