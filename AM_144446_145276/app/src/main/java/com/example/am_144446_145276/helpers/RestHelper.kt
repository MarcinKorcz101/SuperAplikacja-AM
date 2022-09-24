package com.example.am_144446_145276.helpers

import com.example.am_144446_145276.api.RestApiService
import com.example.am_144446_145276.data.UserInfo
import org.json.JSONArray
import org.json.JSONTokener
import java.net.URL

class RestHelper {

    val baseURL = "http://theruddy0709.net:3000/"
//    private val baseURL = "http://192.168.100.203:3000/"

    fun addUser(username: String, pass: String){
        val apiService = RestApiService()
        val userInfo = UserInfo(userName = username, password = pass)

        apiService.addUser(userInfo){
            if (it?.message != null){
                println(it.message)
            } else {
                println("Error adding user")
            }
        }
    }

    fun findUser(username: String): JSONArray {
        val body = URL(baseURL + "users/${username}").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }
}