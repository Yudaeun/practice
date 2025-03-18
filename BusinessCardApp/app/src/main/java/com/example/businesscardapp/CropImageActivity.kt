package com.example.businesscardapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscardapp.databinding.ActivityCropImageBinding

class CropImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCropImageBinding
    private var imageUri: Uri? = null

    companion object {
        private const val REQUEST_CROP = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCropImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageUri = intent.getParcelableExtra("imageUri")
        imageUri?.let { startCrop(it) }

        binding.btnCrop.setOnClickListener {
            imageUri?.let { startCrop(it)}
        }
    }

    private fun startCrop(uri: Uri) {
        val cropIntent = Intent("com.android.camera.action.CROP").apply {
            setDataAndType(uri, "image/*")
            putExtra("crop", "true")
            putExtra("aspectX", 1)
            putExtra("aspectY", 1)
            putExtra("outputX", 500)
            putExtra("outputY", 500)
            putExtra("return-data", true)
        }

        if (cropIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(cropIntent, REQUEST_CROP)
        } else {
            Toast.makeText(this, "크롭 기능을 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show()
        }
    }


}