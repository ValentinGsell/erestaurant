package fr.isen.gsell.erestaurant.cartHandler

import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.gsell.erestaurant.databinding.ItemBinding

internal class CartAdapter(private var itemsList: MutableList<CartData>, private val onClickListener: View.OnClickListener) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    private lateinit var binding: ItemBinding

    internal inner class MyViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.itemName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CartAdapter.MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}