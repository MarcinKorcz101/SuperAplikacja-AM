package com.example.am_144446_145276.api

import com.example.am_144446_145276.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("newuser")
    fun addUser(@Body userData: UserInfo): Call<DefaultResponse>

    @Headers("Content-Type: application/json")
    @POST("newgame")
    fun addMeeting(@Body meetingData: MeetingInfo): Call<DefaultResponse>

    @Headers("Content-Type: application/json")
    @POST("updateuser")
    fun updateUser(@Body userUpdate: UpdateUserLichess): Call<DefaultResponse>

    @Headers("Content-Type: application/json")
    @POST("updategameopponent")
    fun updateMeetingOppo(@Body meetingOppoUpdate: UpdateMeetingOpponent): Call<DefaultResponse>

    @Headers("Content-Type: application/json")
    @POST("updategameresult")
    fun updateMeetingRes(@Body meetingResUpdate: UpdateMeetingResult): Call<DefaultResponse>
}