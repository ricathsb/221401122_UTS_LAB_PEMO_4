package com.mightysana.utspmo

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar

class DetailActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ambil preferensi tema dari SharedPreferences
        sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        setContentView(R.layout.activity_detail)

        val player = intent.getSerializableExtra("player") as? Player
        setupToolbar(player?.name)
        findViewById<SwitchCompat>(R.id.themeSwitch)?.visibility =
            if (this is DetailActivity) View.GONE else View.VISIBLE

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
            positionTextView.text = "Position: ${it.position.getPositionFullName()}"
            nationalityTextView.text = "Nationality: ${it.nationality}"
            birthDateTextView.text = "Birth date: ${it.birthDate} (${it.birthDate.age()})"
            heightTextView.text = "Height: ${it.height} cm"
            numberTextView.text = "Number : ${it.number}"

            val marketValueText = it.marketValue.formatAsCurrency()
            marketValueTextView.text = "Market value: $marketValueText"

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
