package com.mightysana.utspmo

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun AppCompatActivity.setupToolbar() {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    when (this) {
        is MainActivity -> {
            // Hide the back button in MainActivity
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        is DetailActivity -> {
            // Show the back button in DetailActivity
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { onBackPressed() }

        }
        is ManagerActivity -> {
            // Show the back button in ManagerActivity
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { onBackPressed() }

        }
    }
}



fun Double.formatAsCurrency(): String {
    return if (this < 1.0) {
        "€${(this * 1000).toInt()}k"
    } else {
        "€${this} million"
    }
}

fun String.age(): String {
    val today = LocalDate.now()
    val birthday = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    return "${Period.between(birthday, today).years} years old"
}
fun String.getPositionFullName(): String {
    val positionMap = mapOf(
        "GK" to "Goalkeeper",
        "CB" to "Center Back",
        "LB" to "Left Back",
        "RB" to "Right Back",
        "DMF" to "Defensive Midfielder",
        "CMF" to "Central Midfielder",
        "AMF" to "Attacking Midfielder",
        "LWF" to "Left Wing Forward",
        "RWF" to "Right Wing Forward",
        "CF" to "Center Forward"
    )
    return positionMap[this] ?: "Unknown"
}







