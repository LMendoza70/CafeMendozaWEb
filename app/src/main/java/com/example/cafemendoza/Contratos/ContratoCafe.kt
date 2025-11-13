package com.example.cafemendoza.Contratos

import com.example.cafemendoza.Modelos.Cafe

interface ContratoCafe {

    interface Vista{
        fun loadData(Data: List<Cafe>)

    }

    interface Presentador{
        fun loadData()
    }

    interface Modelo{
        fun loadCafe(): List<Cafe>
    }
}


