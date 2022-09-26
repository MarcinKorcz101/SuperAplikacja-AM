package com.example.am_144446_145276.api

import com.example.am_144446_145276.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun addUser(userData: UserInfo, onResult: (DefaultResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun addMeeting(meetingData: MeetingInfo, onResult: (DefaultResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addMeeting(meetingData).enqueue(
            object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun updateUserLichess(userUpdate: UpdateUserLichess, onResult: (DefaultResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.updateUser(userUpdate).enqueue(
            object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun updateMeetingOppo(meetingOppoUpdate: UpdateMeetingOpponent, onResult: (DefaultResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.updateMeetingOppo(meetingOppoUpdate).enqueue(
            object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun updateMeetingRes(meetingResUpdate: UpdateMeetingResult, onResult: (DefaultResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.updateMeetingRes(meetingResUpdate).enqueue(
            object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    onResult(response.body())
                }
            }
        )
    }
}