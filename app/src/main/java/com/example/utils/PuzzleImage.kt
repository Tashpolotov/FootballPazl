package com.example.utils

import android.graphics.Bitmap

class PuzzleImage(private val originalImage:Bitmap, private val numRows:Int, private val numCols:Int) {

    private val width: Int = originalImage.width / numCols

    private val height: Int = originalImage.height / numRows


    fun splitImage(): List<Bitmap> {

        val pieces = ArrayList<Bitmap>(numRows * numCols)

        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                val x = col * width
                val y = row * height
                val piece = Bitmap.createBitmap(originalImage, x, y, width, height)
                pieces.add(piece)
            }
        }

        return pieces
    }
}