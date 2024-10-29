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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescription)
        val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        val itemNumber: TextView = itemView.findViewById(R.id.itemNumber)
    }

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
        holder.itemNumber.text = player.number.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("player", player)
            }
            context.startActivity(intent)
        }
    }


    override fun getItemCount() = playerList.size


    fun updateData(newPlayerList: List<Player>) {
        playerList = newPlayerList
        notifyDataSetChanged()
    }
}
