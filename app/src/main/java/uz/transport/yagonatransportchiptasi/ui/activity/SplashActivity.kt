package uz.transport.yagonatransportchiptasi.ui.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.SharePref
import uz.transport.yagonatransportchiptasi.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT



        if (SharePref(this).isSaved) {
            open()
        } else {
            binding.tvOpenMain.setOnClickListener {
                SharePref(this).isSaved = true
                open()
            }
        }
    }

    fun open() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}