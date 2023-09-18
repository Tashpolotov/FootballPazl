package com.example.footballpazl.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentTransaction

import com.example.footballpazl.R

import com.example.footballpazl.databinding.ActivityMainBinding
import com.example.footballpazl.fragment.HomeFragment
import com.example.footballpazl.fragment.PazleFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FilePermission
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fr_container, homeFragment) // Замените HomeFragment() на создание экземпляра фрагмента, который вы хотите показать.
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

private fun FragmentTransaction.replace(frContainer: Int, homeFragment: HomeFragment) {

}
