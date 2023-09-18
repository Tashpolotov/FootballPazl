package com.example.footballpazl.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class PhotoViewModel : ViewModel() {
    private val _assembledPhoto = MutableStateFlow<Bitmap?>(null)
    val assembledPhoto: StateFlow<Bitmap?> = _assembledPhoto

    fun setAssembledPhoto(photo:Bitmap) {
        _assembledPhoto.value = photo
        Log.d("PhotoViewModel", "assembledPhoto set: $photo")
    }
}