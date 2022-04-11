package fr.isen.gsell.erestaurant.cartHandler

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.gsell.erestaurant.R
import fr.isen.gsell.erestaurant.databinding.CartItemBinding
import fr.isen.gsell.erestaurant.databinding.ItemBinding

internal class CartAdapter(private var itemsList: MutableList<CartData>, private val onClickListener: OnClickListener, private val context: Context) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    private lateinit var binding: CartItemBinding

    internal inner class MyViewHolder(binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.nameItem
        val quantity = binding.quantity
        val totalPrice = binding.itemTotalPrice
        val deleteBtn = binding.delete
        val commentary = binding.commentary
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.name.text = item.name.toString()
        holder.quantity.text = item.qty.toString()
        //holder.totalPrice.text = context.getString(R.string.euro,(item.))
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class OnClickListener(val clickListener: (item: CartData) -> Unit) {
        fun onClick(item: CartData) = clickListener(item)
    }
}