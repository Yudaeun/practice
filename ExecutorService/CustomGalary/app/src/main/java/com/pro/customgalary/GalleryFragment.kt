package com.pro.customgalary

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions

class GalleryFragment : Fragment() {
    private var imageView: ImageView? = null
    private var imageUri: String? = null
    private var imageBitmap: Bitmap? = null
    private var btnEdit: Button? = null

    companion object {
        private const val ARG_IMAGE_URI = "image_uri"
        private const val ARG_IMAGE_BITMAP = "image_bitmap"

        // Uri로 새로운 인스턴스 생성
        fun newInstance(uri: String): GalleryFragment {
            val fragment = GalleryFragment()
            val args = Bundle()
            args.putString(ARG_IMAGE_URI, uri)
            fragment.arguments = args
            return fragment
        }

        // Bitmap으로 새로운 인스턴스 생성
        fun newInstance(photo: Bitmap): GalleryFragment {
            val fragment = GalleryFragment()
            val args = Bundle()
            args.putParcelable(ARG_IMAGE_BITMAP, photo)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 전달된 인자를 읽고 uri 또는 bitmap 설정
        arguments?.let {
            imageUri = it.getString(ARG_IMAGE_URI)
            imageBitmap = it.getParcelable(ARG_IMAGE_BITMAP)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        imageView = view.findViewById(R.id.image_view)
        btnEdit = view.findViewById(R.id.btn_edit)

        imageUri?.let {
            imageView?.setImageURI(Uri.parse(it))
        }

        imageBitmap?.let {
            imageView?.setImageBitmap(it)
        }

        btnEdit?.setOnClickListener {
            if (imageUri != null) {
                startCrop(Uri.parse(imageUri))
            } else {
//                startCrop(imageBitmap)
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        imageView?.setImageDrawable(null)
        imageView = null
    }

    private val cropImageLauncher = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(requireContext())

            imageView?.setImageURI(uriContent)
        } else {
            val e = result.error
        }
    }


    private fun startCrop(uri: Uri) {
        cropImageLauncher.launch(
            CropImageContractOptions(
                uri = uri,
                cropImageOptions = CropImageOptions(
                    outputCompressFormat = Bitmap.CompressFormat.PNG
                )
            )
        )
    }

}