package com.example.pokedex


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokeAdapter(private val pokeList: MutableList<Triple<String, String, String>>) :
    RecyclerView.Adapter<PokeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokeImage: ImageView
        val name: TextView = itemView.findViewById(R.id.name)
        val height: TextView = itemView.findViewById(R.id.charId)

        init {
            // Find our RecyclerView item's ImageView for future use
            pokeImage = view.findViewById(R.id.image)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.pokeImage.setOnClickListener{
            Toast.makeText(holder.itemView.context, "Poke at position $position clicked", Toast.LENGTH_SHORT).show()

        }
        Glide.with(holder.itemView)
            .load(pokeList[position].first)
            .fitCenter()
            .into(holder.pokeImage)

        holder.name.text = pokeList[position].second
        holder.height.text = pokeList[position].third



    }

    override fun getItemCount() = pokeList.size

}






