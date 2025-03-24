package com.example.businesscardapp

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.businesscardapp.databinding.FragmentCardDetailBinding
import java.io.File
import java.io.FileOutputStream

class CardDetailFragment: Fragment() {
    private lateinit var binding: FragmentCardDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardDetailBinding.inflate(inflater, container, false)

        val imagePath = arguments?.getString("imagePath")
        imagePath?.let {
            val file = File(imagePath)
            if (file.exists()) {
                binding.imageView.setImageURI(Uri.fromFile(file))
            } else {
                Toast.makeText(requireContext(), "이미지를 저장할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    companion object {
        fun newInstance(imagePath: String) = CardDetailFragment().apply {
            arguments = Bundle().apply {
                putString("imagePath", imagePath)
            }
        }
    }
    fun copyUriToInternalStorage(context: Context, uri: Uri): String? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val file = File(context.cacheDir, "selected_image.jpg") // 내부 저장소 캐시에 저장
            val outputStream = FileOutputStream(file)

            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()

            return file.absolutePath // ✅ 로컬 파일 경로 반환
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}