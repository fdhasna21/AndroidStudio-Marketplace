package com.fdhasna21.yeouthmarketplace

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fdhasna21.yeouthmarketplace.SignInUp.SignActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        setContentView(R.layout.activity_splash_screen)
        //TODO : make status bar text darker
        val timer = object : Thread() {
            override fun run() {
                try {
                    synchronized(this) { sleep(6000) }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    //TODO : if apiToken is stated, intent to MainApplication
                    val intent = Intent(this@SplashScreen, SignActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        timer.start()
    }
}