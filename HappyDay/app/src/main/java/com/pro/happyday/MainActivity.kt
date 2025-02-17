package com.pro.happyday

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pro.happyday.receiver.ScreenUnlockReceiver
import com.pro.happyday.service.UnlockService

class MainActivity : AppCompatActivity() {
    private lateinit var screenUnlockReceiver: ScreenUnlockReceiver

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        screenUnlockReceiver = ScreenUnlockReceiver()
        val intentFilter= IntentFilter(Intent.ACTION_USER_PRESENT)
        registerReceiver(screenUnlockReceiver, intentFilter)

        val serviceIntent = Intent(this,UnlockService::class.java)
        startForegroundService(serviceIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(screenUnlockReceiver)
    }
}