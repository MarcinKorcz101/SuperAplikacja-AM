package com.example.am_144446_145276

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


@SuppressLint("Cus", "CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        fun startAnimation(context: Context) {
            val splashImage = findViewById<ImageView>(R.id.splashScreenImage)

            val animationSet = AnimatorSet()

            val scaleY = ObjectAnimator.ofFloat(splashImage, "scaleY", 35f, 1f).setDuration(2000)
            val scaleX = ObjectAnimator.ofFloat(splashImage, "scaleX", 35f, 1f).setDuration(2000)

            animationSet.playTogether(scaleX, scaleY)
            animationSet.start()
            animationSet.addListener(object : AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    // ...
                }

                override fun onAnimationRepeat(animation: Animator) {
                    // ...
                }

                override fun onAnimationEnd(animation: Animator) {
                    Handler().postDelayed({
                        val intent = Intent(context, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 1000)
                }

                override fun onAnimationCancel(animation: Animator) {
                    // ...
                }
            })
        }

        startAnimation(this)


    }
}