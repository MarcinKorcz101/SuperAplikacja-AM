package com.example.am_144446_145276.api

import com.example.am_144446_145276.data.DefaultResponse
import com.example.am_144446_145276.data.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("newuser")
    fun addUser(@Body userData: UserInfo): Call<DefaultResponse>
}