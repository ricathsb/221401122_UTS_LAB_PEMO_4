package com.mightysana.utspmo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val titles = resources.getStringArray(R.array.item_titles)
        val descriptions = resources.getStringArray(R.array.item_descriptions)
        val images = resources.obtainTypedArray(R.array.item_images)

        val itemList = titles.indices.map { index ->
            Item(
                title = titles[index],
                description = descriptions[index],
                imageResId = images.getResourceId(index, -1)
            )
        }

        images.recycle()

        val adapter = ItemAdapter(this, itemList)
        recyclerView.adapter = adapter
    }
}
