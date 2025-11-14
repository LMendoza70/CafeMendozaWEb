package com.example.cafemendoza.Modelos

data class respuestaLogin(
    val alias: String,
    val contrasena: String,
    val nombre_completo: String,
    val rol: String,
    val success: Boolean
)
