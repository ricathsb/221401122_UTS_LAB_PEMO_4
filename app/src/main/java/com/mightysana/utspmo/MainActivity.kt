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

        val titles = resources.getStringArray(R.array.player_name)
        val descriptions = resources.getStringArray(R.array.player_position)
        val images = resources.obtainTypedArray(R.array.player_images)
        val nationalities = resources.getStringArray(R.array.player_nationality)
        val birthDates = resources.getStringArray(R.array.player_birth_date)
        val heights = resources.getStringArray(R.array.player_height)
//      val weights = resources.getStringArray(R.array.player_weight)
        val marketValues = resources.getStringArray(R.array.player_market_value)

        val playerList = titles.indices.map { index ->
            Player(
                name = titles[index],
                position = descriptions[index],
                imageResId = images.getResourceId(index, -1),
                nationality = nationalities[index],
                birthDate = birthDates[index],
                height = heights[index],
                marketValue = marketValues[index].toDouble()
            )
        }

        images.recycle()

        val adapter = ItemAdapter(this, playerList)
        recyclerView.adapter = adapter
    }
}
