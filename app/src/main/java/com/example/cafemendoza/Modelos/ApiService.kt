package com.example.cafemendoza.Modelos

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("listacafes.php")
    fun getCafes(): Call<List<WebCafe>>

    @FormUrlEncoded
    @POST("registrocafe.php")
    fun postCafe(
        @Field("nombre")nombre: String,
        @Field("variedad_de_cafe")variedad_de_cafe: String,
        @Field("finca_id")finca_id: Int,
        @Field("proceso")proceso: String,
        @Field("notas_de_cata")notas_de_cata: String,
        @Field("puntuacion_sca")puntuacion_sca: Float,
        @Field("productor_id")productor_id: Int,
        @Field("altura_siembra")altura_siembra: Int,
        @Field("tostador_id")tostador_id: Int,
        @Field("nombre_imagen")nombre_imagen: String): Call<List<respuestaRegistro>>
}

