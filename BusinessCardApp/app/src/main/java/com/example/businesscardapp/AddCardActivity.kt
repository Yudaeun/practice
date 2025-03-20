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
            imageUri?.let {
                val card = Card(name = name, phoneNumber = phone, imagePath = it.toString())
                cardViewModel.insert(card)
                finish()
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
}