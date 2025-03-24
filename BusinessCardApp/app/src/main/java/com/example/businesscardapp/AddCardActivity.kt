package com.example.businesscardapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.businesscardapp.data.Card
import com.example.businesscardapp.databinding.ActivityAddCardBinding
import com.example.businesscardapp.databinding.FragmentCardDetailBinding
import java.io.File
import java.io.FileOutputStream

class AddCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCardBinding
    private lateinit var cardViewModel: CardViewModel
    private var imageUri: Uri? = null

    companion object {
        private const val REQUEST_SELECT_IMAGE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)

        binding.btnSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_SELECT_IMAGE)
        }

        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val phone = binding.editPhone.text.toString()
            imageUri?.let { uri ->
                val localPath = copyUriToInternalStorage(uri)
                if (localPath != null) {
                    val card = Card(name = name, phoneNumber = phone, imagePath = localPath)
                    cardViewModel.insert(card)
                    finish()
                } else {
                    Toast.makeText(this, "이미지를 저장할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            } ?: Toast.makeText(this, "이미지를 선택하세요.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK) {
            imageUri = data?.data
            imageUri?.let { binding.imgPreview.setImageURI(it) }
        }
    }

    private fun copyUriToInternalStorage(uri: Uri): String? {
        return try {
            val inputStream = contentResolver.openInputStream(uri) ?: return null
            val file = File(cacheDir, "card_${System.currentTimeMillis()}.jpg")
            val outputStream = FileOutputStream(file)

            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}