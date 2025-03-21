package com.example.businesscardapp

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.businesscardapp.databinding.FragmentCardDetailBinding

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
            val uri = Uri.parse(it)

            try {
                val inputStream = requireContext().contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.imageView.setImageBitmap(bitmap)
                inputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "이미지를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
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

}