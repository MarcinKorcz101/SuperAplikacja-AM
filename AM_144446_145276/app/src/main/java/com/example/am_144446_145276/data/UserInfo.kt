package com.example.am_144446_145276.data

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("user_name") val userName: String?,
    @SerializedName("password") val password: String?
)
