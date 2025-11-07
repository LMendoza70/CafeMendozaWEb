package com.example.cafemendoza.Modelos

data class WebCafe(val nombre : String,
                   val variedad_de_cafe: String,
                   val productor_nombre: String,
                   val proceso: String,
                   val notas_de_cata: String,
                   val puntuacion_sca: Float,
                   val altura_siembra: Int,
                   val tostador_nombre: String,
                   val nombre_imagen: String)

/*
"nombre": "Chiapas Honey",
"variedad_de_cafe": "Bourbon",
"productor_nombre": "Luis Ramírez",
"proceso": "Honey",
"notas_de_cata": "Notas de miel, nuez y cacao",
"puntuacion_sca": "86.50",
"altura_siembra": "1350",
"tostador_nombre": "Café Mendoza Tostadores",
"nombre_imagen": "chiapas_honey.jpg"*/
