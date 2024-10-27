package com.mightysana.utspmo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve titles and descriptions from strings.xml
        val titles = resources.getStringArray(R.array.item_titles)
        val descriptions = resources.getStringArray(R.array.item_descriptions)

        // Combine them into itemList
        val itemList = titles.zip(descriptions) { title, description ->
            Item(title, description)
        }

        val adapter = ItemAdapter(this, itemList)
        recyclerView.adapter = adapter
    }
}
