package com.example.footballpazl.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.footballpazl.R
import com.example.footballpazl.adapter.ImageAdapter
import com.example.footballpazl.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class HomeFragment :  Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var mCurrentPhotoPath: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val am = activity?.assets
        try {
            val files = am?.list("img")
            binding.grid.adapter = activity?.let { ImageAdapter(it) }
            binding.grid.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
                val bundle = Bundle()
                bundle.putString("assetName", files!![i % files.size])

                val secondFragment = SecondFragment()
                secondFragment.arguments = bundle

                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fr_container, secondFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        } catch (e: IOException) {
            Toast.makeText(activity, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        if (ContextCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE
            )
        } else {
            val timeStamp = java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val imageFileName = "JPEG_" + timeStamp + "_"
            val storageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
            )
            mCurrentPhotoPath = image.absolutePath
            return image
        }
        return null

    }

    companion object {
        private const val REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 2

    }
}