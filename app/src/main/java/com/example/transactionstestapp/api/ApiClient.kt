package com.example.transactionstestapp.api

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient (
    baseUrl: String,
    /* aqui añadiria los interceptores necesarios para que se ocupende temas como el envio de los
    headers o capturar los fallos de login porque el token ha caducado para luego intentar
    refrescarlo de forma automatica sin que el usuario se entere.
     */
) {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(this)
            .build()
    }

    val service: ApiServices = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(ApiServices::class.java)
        }

}