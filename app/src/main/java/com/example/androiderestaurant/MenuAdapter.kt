package com.example.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class MenuAdapter(private val arrayList: ArrayList<String>) : RecyclerView.Adapter<MenuAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_menu, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = arrayList[position]
        holder.name.text = currentItem.toString()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val name : TextView = itemView.findViewById(R.id.categorie)
    }
}