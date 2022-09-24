package com.example.am_144446_145276.helpers

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

class SharedPreferencesHelper (context: Context) {

    private val USER = "loggedUser"
    private val preferences: SharedPreferences = context.getSharedPreferences("com.example.am_144446_145276.shared", 0)


    fun putLoggedUser(user: JSONObject){
        val edit = preferences.edit()
        edit.putString(USER, user.toString())
        edit.apply()
    }

    fun getLoggedUser(): JSONObject{
        return JSONObject(preferences.getString(USER, """{username: ""}"""))
    }
}