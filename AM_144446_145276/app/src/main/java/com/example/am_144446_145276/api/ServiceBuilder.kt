package com.example.am_144446_145276.api

import com.example.am_144446_145276.helpers.RestHelper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val restHelper = RestHelper()

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(restHelper.baseURL.dropLast(1))
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>) :T{
        return retrofit.create(service)
    }
}