package com.pro.happyday.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.pro.happyday.QuoteActivity
import com.pro.happyday.service.UnlockService

class ScreenUnlockReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("ScreenUnlockReceiver", "잠금 해제 감지")

        if (intent?.action == Intent.ACTION_USER_PRESENT) {

            val serviceIntent = Intent(context, UnlockService::class.java)
            context?.startForegroundService(serviceIntent)

            Log.d("ScreenUnlockReceiver", "QuoteActivity 실행")
            val quoteIntent = Intent(context, QuoteActivity::class.java)
            quoteIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(quoteIntent)
        }

    }
}