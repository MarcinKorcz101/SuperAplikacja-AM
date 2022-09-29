package com.example.am_144446_145276.data

import com.google.gson.annotations.SerializedName


data class DefaultResponse(
    @SerializedName("message") val message: String?
)

data class UserInfo(
    @SerializedName("username") val userName: String?,
    @SerializedName("password") val password: String?
)

data class MeetingInfo(
    @SerializedName("host") val host: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("details") val details: String?
)

data class UpdateUserLichess(
    @SerializedName("username") val userName: String?,
    @SerializedName("lichessNick") val lichessNick: String?
)

data class UpdateMeetingOpponent(
    @SerializedName("idgames") val idgames: String?,
    @SerializedName("opponent") val opponent: String?
)

data class UpdateMeetingResult(
    @SerializedName("idgames") val idgames: String?,
    @SerializedName("result") val result: String?
)


