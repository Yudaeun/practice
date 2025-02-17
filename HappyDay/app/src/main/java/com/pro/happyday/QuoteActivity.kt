package com.pro.happyday

import android.app.ComponentCaller
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class QuoteActivity : AppCompatActivity() {

    private val quotes = listOf(
        "어제보다 나은 오늘을 살자.",
        "작은 성취들이 큰 성공을 만든다.",
        "포기하지 않으면 실패도 없다.",
        "노력은 배신하지 않는다.",
        "현재의 노력이 미래를 결정한다."
    )

    private lateinit var windowManager: WindowManager
    private lateinit var dialogView: View

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivityForResult(intent, 1234)
        } else {
            showOverlayDialog()
        }

//        val randomQuote = quotes.random()
//
//        val builder = AlertDialog.Builder(this)
//        val dialogView = layoutInflater.inflate(R.layout.dialog_quote, null)
//
//        val quoteTextView: TextView = dialogView.findViewById(R.id.tv_quote)
//        val btnClose: Button = dialogView.findViewById(R.id.btn_close)
//
//        quoteTextView.text = randomQuote
//
//        builder.setView(dialogView)
//        val dialog=builder.create()
//        dialog.show()
//
//        btnClose.setOnClickListener {
//            dialog.dismiss()
//            finish()
//        }
    }

    private fun showOverlayDialog() {
        windowManager=getSystemService(WINDOW_SERVICE) as WindowManager
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dialogView = inflater.inflate(R.layout.dialog_quote, null)

        // 레이아웃 파라미터 설정
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, // 시스템 오버레이 창
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity= Gravity.CENTER

        windowManager.addView(dialogView, params)

        val randomQuote = quotes.random()
        val quoteTextView: TextView = dialogView.findViewById(R.id.tv_quote)
        quoteTextView.text = randomQuote

        val btnClose: Button = dialogView.findViewById(R.id.btn_close)
        btnClose.setOnClickListener {
            windowManager.removeView(dialogView)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            if (dialogView != null) {
                windowManager.removeView(dialogView)
            }
        } catch (e: IllegalArgumentException) {
            // Handle the case where the view is not attached or already removed
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1234) {
            if (Settings.canDrawOverlays(this)) {
                showOverlayDialog()
            } else {
                Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}