package com.example.cafemendoza.Modelos

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("listacafes.php")
    fun getCafes(): Call<List<WebCafe>>
}
