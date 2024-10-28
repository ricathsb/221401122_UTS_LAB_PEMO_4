package com.mightysana.utspmo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        val titleTextView: TextView = findViewById(R.id.detailTitle)
        val descriptionTextView: TextView = findViewById(R.id.detailDescription)

        titleTextView.text = title
        descriptionTextView.text = description
    }
}
