package com.example.am_144446_145276.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val username: String,
    val password: String,
    val lichessNick: String,
    val createDate: String
): Parcelable
