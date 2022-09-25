package com.example.am_144446_145276

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.am_144446_145276.helpers.RestHelper
import com.example.am_144446_145276.helpers.SharedPreferencesHelper
import org.json.JSONArray
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private val restHelper = RestHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedHelper = SharedPreferencesHelper(this)
        supportActionBar?.hide()

        val loggedUser = sharedHelper.getLoggedUser()
        if (loggedUser.getString("username") == "") {
            val userName = findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)
            val loginButton = findViewById<Button>(R.id.login)
            val registerButton = findViewById<Button>(R.id.register)

            registerButton.setOnClickListener() {
                val nick = userName.text.toString()
                val pass = password.text.toString()
                lateinit var user: JSONArray

                if (nick == "" || pass == "") {
                    Toast.makeText(
                        this,
                        "Username/password can't be empty",
                        Toast.LENGTH_SHORT).show()
                } else if (nick.length > 45 || pass.length > 45) {
                    Toast.makeText(
                        this,
                        "Username/password can't exceed 45 characters",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Thread() {
                        run {
                            user = restHelper.findUser(nick)
                        }
                        runOnUiThread() {
                            if (user.length() == 0) {
                                restHelper.addUser(nick, pass)
                                Toast.makeText(
                                    this,
                                    "Success, now sign in",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this,
                                    "User already exists",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }.start()
                }
            }

            loginButton.setOnClickListener() {
                val view: View? = this.currentFocus
                if (view != null) {
                    val inputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                }
                userName.clearFocus()
                password.clearFocus()
                val nick = userName.text.toString()
                val pass = password.text.toString()
                lateinit var user: JSONArray

                if (nick == "" || pass == "") {
                    Toast.makeText(this, "Username/password can't be empty", Toast.LENGTH_SHORT).show()
                } else if (nick.length > 45 || pass.length > 45) {
                    Toast.makeText(this, "Username/password can't exceed 45 characters", Toast.LENGTH_SHORT).show()
                } else {
                    Thread() {
                        run {
                            user = restHelper.findUser(nick)
                        }
                        runOnUiThread() {
                            if (user.length() == 0) {
                                Toast.makeText(this, "There's no such user", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                if (pass == user.getJSONObject(0).getString("password")) {
                                    sharedHelper.putLoggedUser(JSONObject(user[0].toString()))
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }.start()
                }

            }
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}