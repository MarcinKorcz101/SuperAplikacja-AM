package com.example.am_144446_145276

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.json.JSONArray
import org.json.JSONTokener
import java.net.URL

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userName = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)
        lateinit var jsonArray: JSONArray

        val thread = Thread(){
            run {
                val body = URL("http://theruddy0709.net:3000/games").readText()
                jsonArray = JSONTokener(body).nextValue() as JSONArray
            }
            runOnUiThread(){
                println(jsonArray)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        loginButton.setOnClickListener(){
            val nick = userName.text.toString()
            val pass = password.text.toString()

            println(nick)
            println(pass)

            thread.start()
        }
    }
}