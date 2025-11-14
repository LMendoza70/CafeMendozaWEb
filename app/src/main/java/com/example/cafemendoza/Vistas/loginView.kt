package com.example.cafemendoza.Vistas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cafemendoza.Modelos.ApiService
import com.example.cafemendoza.Modelos.respuestaLogin
import com.example.cafemendoza.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class loginView : AppCompatActivity() {

    //inicializamos los componentes de la vista
    private lateinit var alias: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var loginService: ApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //inicializamos los componentes de la vista
        alias = findViewById(R.id.alias_input)
        password = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        //configuramos retrofit
        val retrofit= Retrofit.Builder()
            .baseUrl("https://cafemendoza.grupoahost.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //inicializamos el servicio
        loginService = retrofit.create(ApiService::class.java)

        //configuramos el boton de login
        loginButton.setOnClickListener {
            val alias = alias.text.toString()
            val password = password.text.toString()
            login(alias,password)
        }
    }

    private fun login(alias: String,password: String){
        //llamamos a la funcion login del apiservice
        loginService.login(alias,password)
            .enqueue(object:Callback<respuestaLogin>{
                override fun onResponse(
                    call: Call<respuestaLogin?>,
                    response: Response<respuestaLogin?>
                ) {
                    if(response.isSuccessful){
                        val respuesta = response.body()
                        if(respuesta!=null && respuesta.success==true){
                            //loginexitoso
                            navegarInicio(respuesta.nombre_completo,respuesta.rol)
                        }else{
                            //login incorrecto
                            Toast.makeText(this@loginView,"Login incorrecto usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        //login incorrecto
                        Toast.makeText(this@loginView,"Error al conectar",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<respuestaLogin?>,
                    t: Throwable
                ) {
                    Toast.makeText(this@loginView,"Error al conectar automatico",Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun navegarInicio(nombreCompleto: String, rol: String) {
        // Creamos un Intent para ir desde esta Activity (loginView) a cafeWebview
        val intent = Intent(this, CafeWebView::class.java)

        // Agregamos los datos que queremos pasar a la siguiente Activity
        intent.putExtra("NOMBRE_COMPLETO", nombreCompleto)
        intent.putExtra("ROL", rol)

        // Iniciamos la nueva Activity
        startActivity(intent)

        // Opcional: Finaliza la Activity de login para que el usuario no pueda volver a ella con el botón "atrás"
        finish()
    }
}