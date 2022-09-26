package com.example.am_144446_145276.helpers

import com.example.am_144446_145276.api.RestApiService
import com.example.am_144446_145276.data.*
import org.json.JSONArray
import org.json.JSONTokener
import java.net.URL

class RestHelper {

    val baseURL = "http://theruddy0709.net:3000/"
//    private val baseURL = "http://192.168.100.203:3000/"

    val apiService = RestApiService()

    fun addUser(username: String, pass: String){
        val userInfo = UserInfo(username, pass)

        apiService.addUser(userInfo){
            if (it?.message != null){
                println(it.message)
            } else {
                println("Error adding user")
            }
        }
    }

    fun addGame(host: String, date: String, coords: String, details: String){
        val meetingInfo = MeetingInfo(host, date, coords, details)

        apiService.addMeeting(meetingInfo){
            if (it?.message != null){
                println(it.message)
            } else {
                println("Error adding meeting")
            }
        }
    }

    fun updateUser(username: String, lichessNick: String){
        val updateUserLichess = UpdateUserLichess(username, lichessNick)

        apiService.updateUserLichess(updateUserLichess){
            if (it?.message != null){
                println(it.message)
            } else {
                println("Error updating user's lichess nick")
            }
        }
    }

    fun updateMeetingOppo(idgames: String, opponent: String){
        val updateMeetingOpponent = UpdateMeetingOpponent(idgames, opponent)

        apiService.updateMeetingOppo(updateMeetingOpponent){
            if (it?.message != null){
                println(it.message)
            } else {
                println("Error adding meeting's opponent")
            }
        }
    }

    fun updateMeetingRes(idgames: String, result: String){
        val updateMeetingRes = UpdateMeetingResult(idgames, result)

        apiService.updateMeetingRes(updateMeetingRes){
            if (it?.message != null){
                println(it.message)
            } else {
                println("Error adding meeting's result")
            }
        }
    }

    fun getAllUsers(): JSONArray{
        val body = URL(baseURL + "users").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun findUser(username: String): JSONArray {
        val body = URL(baseURL + "users/${username}").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getAllGames(): JSONArray{
        val body = URL(baseURL + "games").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getFutureGames(): JSONArray{
        val body = URL(baseURL + "futuregames").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getFutureGamesUser(username: String): JSONArray{
        val body = URL(baseURL + "futuregames/${username}").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getAllGamesWithUser(username: String): JSONArray{
        val body = URL(baseURL + "games/${username}").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getAllGamesWonByUser(username: String): JSONArray{
        val body = URL(baseURL + "games/${username}/won").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getAllGamesLostByUser(username: String): JSONArray{
        val body = URL(baseURL + "games/${username}/lost").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun findGameById(id: String): JSONArray{
        val body = URL(baseURL + "game/${id}").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }
}