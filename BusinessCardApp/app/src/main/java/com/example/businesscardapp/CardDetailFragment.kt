package com.example.businesscardapp

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        imagePath?.let { binding.imageView.setImageURI(Uri.parse(it)) }

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