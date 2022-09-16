package com.example.am_144446_145276

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userName = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)

        loginButton.setOnClickListener(){
            val nick = userName.text.toString()
            val pass = password.text.toString()

            println(nick)
            println(pass)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}