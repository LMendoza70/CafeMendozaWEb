package com.example.cafemendoza.Modelos

import com.example.cafemendoza.Contratos.ContratoCafe

class CafeModelo: ContratoCafe.Modelo {

    lateinit var lista: List<Cafe>

    override fun loadCafe(): List<Cafe>{
        lista=listOf(
            Cafe(1,1,"H19G","Cafe H19 en Grano",99.00,18),
            Cafe(1,2,"H19M","Cafe H19 en Molido",99.00,18),
            Cafe(1,3,"H16G","Cafe H16 en Grano",80.00,2),
            Cafe(1,4,"H16M","Cafe H16 en Molido",80.00,2),
            Cafe(1,5,"H29G","Cafe H29 en Grano",150.00,2),
            Cafe(1,6,"H29M","Cafe H29 en Molido",150.00,2),
            Cafe(1,7,"H24M","Cafe H24 en Molido",120.00,2),
            Cafe(1,8,"H24G","Cafe H24 en Grano",120.00,2)
        )
        return lista
    }

}