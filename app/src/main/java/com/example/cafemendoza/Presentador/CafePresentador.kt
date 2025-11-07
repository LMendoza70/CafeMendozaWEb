package com.example.cafemendoza.Presentador

import com.example.cafemendoza.Contratos.ContratoCafe
import com.example.cafemendoza.Modelos.CafeModelo

class CafePresentador(val vista: ContratoCafe.Vista): ContratoCafe.Presentador {
    val modelo= CafeModelo()

    override fun loadData() {
        val lista=modelo.loadCafe()
        vista.loadData(lista)
    }
}