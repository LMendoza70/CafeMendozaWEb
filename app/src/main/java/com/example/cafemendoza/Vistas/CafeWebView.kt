package com.example.cafemendoza.Vistas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafemendoza.Contratos.ContratoCafe
import com.example.cafemendoza.Modelos.ApiService
import com.example.cafemendoza.Modelos.Cafe
import com.example.cafemendoza.Modelos.WebCafe
import com.example.cafemendoza.R
import com.google.gson.internal.GsonBuildConfig
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CafeWebView : AppCompatActivity(), ContratoCafe.Vista {
    private lateinit var rcvLista: RecyclerView
    private lateinit var service: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cafe_web_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rcvLista = findViewById(R.id.rcvCafeWeb)
        rcvLista.layoutManager= LinearLayoutManager(this)

        //configuramos retrofit //esto iria en el modelo
        val retrofit= Retrofit.Builder()
            .baseUrl("https://cafemendoza.grupoahost.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        service=retrofit.create(ApiService::class.java)
        cargarPeliculas()
    }

    private fun cargarPeliculas(){
        service.getCafes().enqueue(object :retrofit2.Callback<List<WebCafe>>{
            override fun onResponse(
                call: Call<List<WebCafe>?>,
                response: Response<List<WebCafe>?>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cafes ->
                        Toast.makeText(baseContext, "Se encontraron ${cafes.size} cafes", Toast.LENGTH_SHORT).show()
                        val adaptador= CafeAdapterWeb(this@CafeWebView,cafes)
                        rcvLista.adapter=adaptador
                    }
                }else{
                    Toast.makeText(baseContext, "Error al cargar los datos ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                call: Call<List<WebCafe>?>,
                t: Throwable
            ) {
                Toast.makeText(baseContext, "Error al cargar los datos ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })



    }

    override fun loadData(Data: List<Cafe>) {
        TODO("Not yet implemented")
    }
}