package com.mightysana.utspmo
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val context: Context, private val itemList: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemTitle.text = item.title
        holder.itemDescription.text = item.description

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title", item.title)
                putExtra("description", item.description)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = itemList.size
}
