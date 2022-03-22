package com.example.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiderestaurant.model.Item


internal class MenuAdapter(private var itemsList: ArrayList<Item>, val clickListener: (Item) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var prix: TextView = view.findViewById(R.id.itemPrice)
        var itemTextView: TextView = view.findViewById(R.id.itemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.name_fr
        holder.prix.text = item.prices[0].price +" €"

        holder.itemView.setOnClickListener{
            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}