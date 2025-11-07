package com.example.cafemendoza.Modelos
import retrofit2.http.GET
import retrofit2.Call
interface ifaceApiService {
    @GET("serviceCafe.php")
    fun readCafe(): Call<List<WebCafe>>

}