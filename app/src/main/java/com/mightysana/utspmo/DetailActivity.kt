package com.mightysana.utspmo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val player = intent.getSerializableExtra("player") as? Player
        setupToolbar(player?.name)

        val titleTextView: TextView = findViewById(R.id.detailTitle)
        val positionTextView: TextView = findViewById(R.id.detailPosition)
        val nationalityTextView: TextView = findViewById(R.id.detailNationality)
        val birthDateTextView: TextView = findViewById(R.id.detailBirthDate)
        val heightTextView: TextView = findViewById(R.id.detailHeight)
        val marketValueTextView: TextView = findViewById(R.id.detailMarketValue)
        val imageView: ImageView = findViewById(R.id.itemImage)
        val numberTextView: TextView = findViewById(R.id.itemNumber)

        player?.let {
            titleTextView.text = it.name
            positionTextView.text = it.position
            nationalityTextView.text = it.nationality
            birthDateTextView.text = "${it.birthDate} (${it.birthDate.age()})"
            heightTextView.text = "${it.height} cm"
            numberTextView.text = it.number.toString()

            val marketValueText = it.marketValue.formatAsCurrency()
            marketValueTextView.text = marketValueText

            val imageResId = it.imageResId
            if (imageResId != -1) {
                imageView.setImageResource(imageResId)
            }
        }
    }

    private fun setupToolbar(playerName: String?) {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        supportActionBar?.title = playerName ?: "Detail Player"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
