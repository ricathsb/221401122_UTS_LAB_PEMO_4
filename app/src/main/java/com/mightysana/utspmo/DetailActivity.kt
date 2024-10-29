package com.mightysana.utspmo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupToolbar()

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
        val numberTextView: TextView = findViewById(R.id.itemNumber)

        // Set the player data to views
        player?.let {
            titleTextView.text = it.name
            positionTextView.text = it.position
            nationalityTextView.text = it.nationality
            birthDateTextView.text = "${it.birthDate} (${it.birthDate.age()})"
            heightTextView.text = "${it.height} cm"
            numberTextView.text = it.number.toString()

            // Set market value with conditional formatting
            val marketValueText = it.marketValue.formatAsCurrency()
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
