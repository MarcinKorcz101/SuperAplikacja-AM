package com.example.am_144446_145276.helpers

import com.example.am_144446_145276.api.RestApiService
import com.example.am_144446_145276.data.*
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import java.time.format.DateTimeFormatter

class RestHelper {

    val baseURL = "http://theruddy0709.net:3000/"
//    private val baseURL = "http://192.168.100.203:3000/"

    private val apiService = RestApiService()

    private fun arrayJSONToMeetingConverter(jsonArray: JSONArray): ArrayList<Meeting> {
        val tmpList : ArrayList<Meeting> = ArrayList()
        for (i in 0 until jsonArray.length()){
            tmpList.add(
                Meeting(
                    jsonArray.getJSONObject(i).getLong("idgames"),
                    jsonArray.getJSONObject(i).getString("host"),
                    jsonArray.getJSONObject(i).getString("opponent"),
                    jsonArray.getJSONObject(i).getString("address"),
                    jsonArray.getJSONObject(i).getString("date").dropLast(8)
                        .replace("T", " ")
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    jsonArray.getJSONObject(i).getString("details"),
                    jsonArray.getJSONObject(i).getString("result")
                )
            )
        }
        return tmpList
    }

    private fun arrayJSONToUserConverter(jsonArray: JSONArray): ArrayList<User> {
        val tmpList : ArrayList<User> = ArrayList()
        for (i in 0 until jsonArray.length()){
            tmpList.add(
                User(
                    jsonArray.getJSONObject(i).getString("username"),
                    jsonArray.getJSONObject(i).getString("password"),
                    jsonArray.getJSONObject(i).getString("lichessNick"),
                    jsonArray.getJSONObject(i).getString("createDate").dropLast(8)
                        .replace("T", " ")
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                )
            )
        }
        return tmpList
    }

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

    fun addGame(host: String, date: String, address: String, details: String){
        val meetingInfo = MeetingInfo(host, date, address, details)

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

    fun getLichessUserInfo(username: String): JSONObject{
        val body = URL("https://lichess.org/api/user/$username").readText()
        println(body)
        return JSONTokener(body).nextValue() as JSONObject
    }

    fun getAllGames(): JSONArray{
        val body = URL(baseURL + "games").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getFutureGames(): JSONArray{
        val body = URL(baseURL + "futuregames").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getEmptyGames(): ArrayList<Meeting>{
        val body = URL(baseURL + "emptygames").readText()
        val jsonArray = JSONTokener(body).nextValue() as JSONArray
        return arrayJSONToMeetingConverter(jsonArray)
    }

    fun getEmptyGamesUser(username: String): ArrayList<Meeting>{
        val body = URL(baseURL + "emptygames/${username}").readText()
        val jsonArray = JSONTokener(body).nextValue() as JSONArray
        return arrayJSONToMeetingConverter(jsonArray)
    }

    fun getFutureGamesUser(username: String): ArrayList<Meeting>{
        val body = URL(baseURL + "futuregames/${username}").readText()
        val jsonArray = JSONTokener(body).nextValue() as JSONArray
        return arrayJSONToMeetingConverter(jsonArray)
    }

    fun getResolvedGamesUser(username: String): ArrayList<Meeting>{
        val body = URL(baseURL + "resolvedgames/${username}").readText()
        val jsonArray = JSONTokener(body).nextValue() as JSONArray
        return arrayJSONToMeetingConverter(jsonArray)
    }

    fun getNotResolvedGamesUser(username: String): ArrayList<Meeting>{
        val body = URL(baseURL + "notresolvedgames/${username}").readText()
        val jsonArray = JSONTokener(body).nextValue() as JSONArray
        return arrayJSONToMeetingConverter(jsonArray)
    }

    fun getAllGamesWithUser(username: String): JSONArray{
        val body = URL(baseURL + "games/${username}").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }

    fun getAllGamesWonByUser(username: String): ArrayList<Meeting>{
        val body = URL(baseURL + "games/${username}/won").readText()
        val jsonArray = JSONTokener(body).nextValue() as JSONArray
        return arrayJSONToMeetingConverter(jsonArray)
    }

    fun getAllGamesLostByUser(username: String): ArrayList<Meeting>{
        val body = URL(baseURL + "games/${username}/lost").readText()
        val jsonArray = JSONTokener(body).nextValue() as JSONArray
        return arrayJSONToMeetingConverter(jsonArray)
    }

    fun getAllGamesTiedByUser(username: String): ArrayList<Meeting>{
        val body = URL(baseURL + "games/${username}/tied").readText()
        val jsonArray = JSONTokener(body).nextValue() as JSONArray
        return arrayJSONToMeetingConverter(jsonArray)
    }

    fun findGameById(id: String): JSONArray{
        val body = URL(baseURL + "game/${id}").readText()
        return JSONTokener(body).nextValue() as JSONArray
    }
}