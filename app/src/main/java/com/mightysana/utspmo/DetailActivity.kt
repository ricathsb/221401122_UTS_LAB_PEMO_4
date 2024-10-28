package com.mightysana.utspmo
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailTitle = findViewById<TextView>(R.id.detailTitle)
        val detailDescription = findViewById<TextView>(R.id.detailDescription)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        detailTitle.text = title
        detailDescription.text = description
    }
}
