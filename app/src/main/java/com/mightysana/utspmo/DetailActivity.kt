package com.mightysana.utspmo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve the Player object passed through the intent
        val player = intent.getSerializableExtra("player") as? Player
        val imageResId = player?.imageResId ?: -1

        // Initialize views
        val titleTextView: TextView = findViewById(R.id.detailTitle)
        val positionTextView: TextView = findViewById(R.id.detailPosition)
        val nationalityTextView: TextView = findViewById(R.id.detailNationality)
        val birthDateTextView: TextView = findViewById(R.id.detailBirthDate)
        val heightTextView: TextView = findViewById(R.id.detailHeight)
        val marketValueTextView: TextView = findViewById(R.id.detailMarketValue)
        val imageView: ImageView = findViewById(R.id.itemImage)

        // Set the player data to views
        player?.let {
            titleTextView.text = it.name
            positionTextView.text = it.position
            nationalityTextView.text = it.nationality
            birthDateTextView.text = it.birthDate
            heightTextView.text = "${it.height} cm"

            // Set market value with conditional formatting
            val marketValueText = if (it.marketValue < 1.0) {
                "€${(it.marketValue * 1000).toInt()}k"
            } else {
                "€${it.marketValue} million"
            }
            marketValueTextView.text = marketValueText

            // Set image if available
            if (imageResId != -1) {
                imageView.setImageResource(imageResId)
            } else {
                imageView.setImageResource(imageResId) // fallback image
            }
        }
    }
}
