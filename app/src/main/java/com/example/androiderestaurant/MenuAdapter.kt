package com.example.androiderestaurant

import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiderestaurant.model.Item
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.reflect.Array.get


internal class MenuAdapter(private var itemsList: ArrayList<Item>, val clickListener: (Item) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var prix: TextView = view.findViewById(R.id.itemPrice)
        var itemTextView: TextView = view.findViewById(R.id.itemName)
        var imageView: ImageView = view.findViewById(R.id.itemImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.name_fr
        holder.prix.text = item.prices[0].price +" €"
        var url: String = "https:\\/\\/cooking-chef.sa.metacdn.com\\/sites\\/default\\/files\\/styles\\/recipe_visuel_node\\/public\\/recettes\\/recette-salade%20lyonnaise.jpg"


        //holder.imageView.setImageDrawable(Drawable.createFromPath("C:\\Users\\valen\\AndroidStudioProjects\\AndroidERestaurant2\\app\\src\\main\\res\\drawable-v24\\il_ristorante.png"))
        Picasso.get().load(item.images[0].ifEmpty { null })
            .placeholder(R.drawable.il_ristorante)
            .into(holder.imageView)


        holder.itemView.setOnClickListener{
            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}