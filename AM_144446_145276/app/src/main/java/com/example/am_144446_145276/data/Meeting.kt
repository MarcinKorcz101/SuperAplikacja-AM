package com.example.am_144446_145276.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDateTime

@Parcelize
data class Meeting(
    val id: Long,
    val hostName: String,
    val opponent: String,
    val gameCoords: String,
    val dateTime: String,
    val details: String,
    val result: String
): Parcelable
