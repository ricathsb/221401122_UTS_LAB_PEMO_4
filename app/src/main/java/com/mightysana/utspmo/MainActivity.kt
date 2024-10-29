package com.mightysana.utspmo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    private lateinit var playerList: List<Player>
    private lateinit var themeSwitch: SwitchCompat
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengatur tema berdasarkan preferensi pengguna
        sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        setContentView(R.layout.activity_main)
        setupToolbar()

        val cardView = findViewById<View>(R.id.card_view) // Replace 'card_view_id' with your actual card ID in item_card_layout

        cardView.setOnClickListener {
            val intent = Intent(this, ManagerActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi Switch untuk Dark Mode
        themeSwitch = findViewById(R.id.themeSwitch)
        themeSwitch.isChecked = isDarkMode

        // Mengatur listener pada themeSwitch
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Ganti tema berdasarkan status switch
            val mode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)

            // Simpan preferensi tema
            with(sharedPreferences.edit()) {
                putBoolean("isDarkMode", isChecked)
                apply()
            }
        }

        setupRecyclerView()
        setupPositionFilter()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val titles = resources.getStringArray(R.array.player_name)
        val descriptions = resources.getStringArray(R.array.player_position)
        val images = resources.obtainTypedArray(R.array.player_images)
        val nationalities = resources.getStringArray(R.array.player_nationality)
        val birthDates = resources.getStringArray(R.array.player_birth_date)
        val heights = resources.getStringArray(R.array.player_height)
        val marketValues = resources.getStringArray(R.array.player_market_value)
        val numbers = resources.getStringArray(R.array.player_number).map { it.toDoubleOrNull()?.toInt() ?: 0 }

        playerList = titles.indices.map { index ->
            Player(
                name = titles[index],
                position = descriptions[index],
                imageResId = images.getResourceId(index, -1),
                nationality = nationalities[index],
                birthDate = birthDates[index],
                height = heights[index],
                number = numbers[index],
                marketValue = marketValues[index].toDouble()
            )
        }.sortedBy { it.number }

        images.recycle()

        adapter = ItemAdapter(this, playerList)
        recyclerView.adapter = adapter
    }

    private fun setupPositionFilter() {
        val positionSpinner = findViewById<Spinner>(R.id.positionSpinner)
        val positions = listOf("All", "GK", "Defender", "Midfielder", "Forward")

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, positions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        positionSpinner.adapter = spinnerAdapter

        positionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedPosition = positions[position]
                filterByPosition(selectedPosition)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun filterByPosition(position: String) {
        val positions = mapOf(
            "GK" to listOf("GK"),
            "Defender" to listOf("CB", "LB", "RB"),
            "Midfielder" to listOf("DMF", "CMF", "AMF", "RMF", "LMF"),
            "Forward" to listOf("LWF", "RWF", "SS", "CF")
        )

        val filteredList = if (position == "All") {
            playerList
        } else {
            playerList.filter { it.position in positions[position]!! }
        }
        adapter.updateData(filteredList)
    }
}
