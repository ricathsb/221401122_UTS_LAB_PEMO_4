package com.mightysana.utspmo

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun AppCompatActivity.setupToolbar() {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    toolbar.setNavigationOnClickListener {
        onBackPressed()
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




