package com.mightysana.utspmo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val context: Context, private var playerList: List<Player>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // ViewHolder class to hold item views for recycling
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescription)
        val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
    }

    // Inflates the item layout and returns the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    // Binds data to each item view in the RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = playerList[position]
        holder.itemTitle.text = player.name
        holder.itemDescription.text = player.position
        holder.itemImage.setImageResource(player.imageResId)

        // Sets a click listener on each item to open DetailActivity with the selected player
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("player", player)
            }
            context.startActivity(intent)
        }
    }

    // Returns the total number of items in the list
    override fun getItemCount() = playerList.size

    // Updates data when filtered or refreshed
    fun updateData(newPlayerList: List<Player>) {
        playerList = newPlayerList
        notifyDataSetChanged()
    }
}
