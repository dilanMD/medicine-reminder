package com.deluxan.medicine.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ProgressBar
import com.deluxan.medicine.utils.enum.LoginPref
import com.deluxan.medicine.utils.enum.SharedPrefType
import com.deluxan.medicine.R

class SplashActivity : AppCompatActivity() {
    private val TAG = SplashActivity::class.java.name
    private lateinit var progressBar: ProgressBar
    private var progressStatus: Int = 0
    private val handler = Handler()
    private var isLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        progressBar = findViewById(R.id.splash_loading)

        handleLoading()
    }

    fun handleLoading() {
        Thread {
            while (progressStatus < 100) {
                progressStatus += 1
                handler.post {
                    progressBar.progress = progressStatus
                    if (progressStatus == 100) {
                        val loginSharedPref = this.getSharedPreferences(
                                SharedPrefType.LOGIN_PREF.getDescription(),
                                Context.MODE_PRIVATE
                        )
                        isLoggedIn = loginSharedPref.getBoolean(
                                LoginPref.IS_LOGGED_IN.getDescription(),
                                false
                        )

                        Log.i(TAG, isLoggedIn.toString())

                        var intent: Intent
                        intent = Intent(this, HomeActivity::class.java)

                        if (isLoggedIn) {
                            intent = Intent(this, HomeActivity::class.java)
                        }

                        startActivity(intent)
                    }
                }
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}