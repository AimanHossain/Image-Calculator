package com.example.imagecalculator

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var combinedImageView: ImageView
    private lateinit var getRandomImagesButton: Button
    private lateinit var combineButton: Button
    private lateinit var clearButton: Button
    private lateinit var infoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        combinedImageView = findViewById(R.id.combinedImageView)
        getRandomImagesButton = findViewById(R.id.getRandomImagesButton)
        combineButton = findViewById(R.id.combineButton)
        clearButton = findViewById(R.id.clearButton)
        infoButton = findViewById(R.id.infoButton)

        // Set click listener for Get Random Images button
        getRandomImagesButton.setOnClickListener {
            displayRandomImages()
        }

        // Set click listener for Combine Images button
        combineButton.setOnClickListener {
            combineImages()
        }

        // Set click listener for Clear button
        clearButton.setOnClickListener {
            clearImages()
        }

        // Set click listener for Info button
        infoButton.setOnClickListener {
            showInstructionsDialog()
        }
    }

    private fun displayRandomImages() {
        val drawableImages = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
        )

        val randomImage1 = drawableImages.random()
        val randomImage2 = drawableImages.random()

        imageView1.setImageResource(randomImage1)
        imageView2.setImageResource(randomImage2)
    }

    private fun combineImages() {
        val drawable1 = imageView1.drawable as BitmapDrawable
        val drawable2 = imageView2.drawable as BitmapDrawable
        val bitmap1 = drawable1.bitmap
        val bitmap2 = drawable2.bitmap

        val combinedBitmap = combineBitmaps(bitmap1, bitmap2)
        combinedImageView.setImageBitmap(combinedBitmap)
    }

    private fun combineBitmaps(bitmap1: Bitmap, bitmap2: Bitmap): Bitmap {
        val width = bitmap1.width.coerceAtLeast(bitmap2.width)
        val height = bitmap1.height + bitmap2.height

        val combinedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(combinedBitmap)
        val paint = Paint()

        // Draw the first image
        canvas.drawBitmap(bitmap1, (width - bitmap1.width) / 2f, 0f, paint)

        // Draw the second image below the first
        canvas.drawBitmap(bitmap2, (width - bitmap2.width) / 2f, bitmap1.height.toFloat(), paint)

        return combinedBitmap
    }

    private fun clearImages() {
        imageView1.setImageResource(0)
        imageView2.setImageResource(0)
        combinedImageView.setImageResource(0)
    }

    private fun showInstructionsDialog() {
        // Create a dialog to display instructions
        val dialogView = layoutInflater.inflate(R.layout.dialog_instructions, null)
        val dialogButton = dialogView.findViewById<Button>(R.id.dialogButton)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialogButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
