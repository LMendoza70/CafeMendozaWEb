package com.example.cafemendoza.Vistas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafemendoza.Contratos.ContratoCafe
import com.example.cafemendoza.Modelos.Cafe
import com.example.cafemendoza.Modelos.CafeModelo
import com.example.cafemendoza.Presentador.CafePresentador
import com.example.cafemendoza.R

class CafeView : AppCompatActivity(), ContratoCafe.Vista {

    lateinit var rcvCafes: RecyclerView
    lateinit var presentador: CafePresentador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cafe_view)

        rcvCafes = findViewById(R.id.rcvCafe)
        rcvCafes.layoutManager= LinearLayoutManager(this)
        presentador= CafePresentador(this)
        presentador.loadData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun loadData(Data: List<Cafe>) {
        rcvCafes.adapter = CafeAdapter(Data)
    }
}