package com.example.cafemendoza.Vistas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cafemendoza.Modelos.ApiService
import com.example.cafemendoza.Modelos.respuestaRegistro
import com.example.cafemendoza.R
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroView : AppCompatActivity() {

    //declaramos los componentes del activity
    private lateinit var nombre: TextInputEditText
    private lateinit var variedad: TextInputEditText
    private lateinit var finca_id: TextInputEditText
    private lateinit var proceso:  TextInputEditText
    private lateinit var notas: TextInputEditText
    private lateinit var puntuacion: TextInputEditText
    private lateinit var productor_id: TextInputEditText
    private lateinit var altura: TextInputEditText
    private lateinit var tostador_id: TextInputEditText
    private lateinit var imagen: TextInputEditText
    private lateinit var boton : Button


    //declaramos el servicio
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //inicializamos los componentes
        nombre= findViewById(R.id.editTextNombre)
        variedad= findViewById(R.id.editTextVariedad)
        finca_id= findViewById(R.id.editTextFincaId)
        proceso= findViewById(R.id.editTextProceso)
        notas= findViewById(R.id.editTextNotasCata)
        puntuacion= findViewById(R.id.editTextPuntuacionSca)
        productor_id= findViewById(R.id.editTextProductorId)
        altura= findViewById(R.id.editTextAlturaSiembra)
        tostador_id= findViewById(R.id.editTextTostadorId)
        imagen= findViewById(R.id.editTextNombreImagen)
        boton= findViewById(R.id.btnRegistrar)

        //configuramos retrofit
        val retrofit= Retrofit.Builder()
            .baseUrl("https://cafemendoza.grupoahost.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //inicializamos el servicio
        apiService= retrofit.create(ApiService::class.java)

        //configuramos nuestro boton
        boton.setOnClickListener{
            val nombre= nombre.text.toString()
            val variedad= variedad.text.toString()
            val finca_id= finca_id.text.toString().toInt()
            val proceso= proceso.text.toString()
            val notas= notas.text.toString()
            val puntuacion= puntuacion.text.toString().toFloat()
            val productor_id= productor_id.text.toString().toInt()
            val altura= altura.text.toString().toInt()
            val tostador_id= tostador_id.text.toString().toInt()
            val imagen= imagen.text.toString()
            registrarCafe(
                nombre,
                variedad,
                finca_id,
                proceso,
                notas,
                puntuacion,
                productor_id,
                altura,
                tostador_id,
                imagen)
        }
    }

    private fun registrarCafe(nombre: String, variedad: String, finca_id: Int,
                              proceso: String, notas: String, puntuacion: Float,
                              productor_id: Int, altura: Int, tostador_id: Int,
                              imagen: String){

        apiService.postCafe(
            nombre,
            variedad,
            finca_id,
            proceso,
            notas,
            puntuacion,
            productor_id,
            altura,
            tostador_id,
            imagen
        ).enqueue(object : Callback<List<respuestaRegistro>>{
            override fun onResponse(
                call: Call<List<respuestaRegistro>?>,
                response: Response<List<respuestaRegistro>?>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(this@RegistroView, "Cafe registrado", Toast.LENGTH_SHORT)
                    response.body()?.let { respuestaRegistros ->
                        if(respuestaRegistros[0].success==true){
                            Toast.makeText(this@RegistroView, respuestaRegistros[0].message, Toast.LENGTH_SHORT)
                            finish()
                        }else{
                            Toast.makeText(this@RegistroView, respuestaRegistros[0].message, Toast.LENGTH_SHORT)
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<List<respuestaRegistro>?>,
                t: Throwable
            ) {
                Toast.makeText(this@RegistroView, t.message, Toast.LENGTH_SHORT)
            }

        })
    }

}