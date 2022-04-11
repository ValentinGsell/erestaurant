package fr.isen.gsell.erestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import fr.isen.gsell.erestaurant.cartHandler.CartData
import fr.isen.gsell.erestaurant.databinding.ActivityDetailBinding
import fr.isen.gsell.erestaurant.model.Item
import pl.polak.clicknumberpicker.ClickNumberPickerListener
import pl.polak.clicknumberpicker.PickerClickType
import java.io.File

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val item = intent.getSerializableExtra(MenuActivity.ITEM_KEY) as Item
        binding.detailTitle.text = item.name_fr

        binding.itemIngredients.text = item.ingredients.joinToString(", ") { it.name_fr}
        Log.d("DetailActivity", item.ingredients[0].toString())

        val carouselAdapter = CarouselAdapter(this, item.images)
        binding.detailSlider.adapter = carouselAdapter

        var qty: Int = 0
        var total: Int = 0
        var value = item.prices[0].price

        binding.add.setOnClickListener {
            qty+=1
            binding.total.setText(("Total :" + item.prices[0].price.toFloat() * qty).toString() + "€")
            binding.quantity.setText("Number of items : " + qty)
            total = qty*item.prices[0].price.toString().toInt()
        }

        binding.remove.setOnClickListener {
            if(qty == 0){
                qty = 0
            }else{
                qty-=1
                binding.total.setText(("Total :" + item.prices[0].price.toFloat() * qty).toString() + "€")
                binding.quantity.setText("Number of items : " + qty)
                total = qty*item.prices[0].price.toString().toInt()

            }

        }

        binding.total.setOnClickListener {
            if(total > 0) {
                var panierData = CartData(item.name_fr, qty, total)
                val jsonString = Gson().toJson(panierData)
                var currentText = ""

                currentText = File(cacheDir.absolutePath+"dataPanier.json").readText()
                File(cacheDir.absolutePath+"dataPanier.json").writeText(currentText + jsonString)
            }
        }


    }
    fun storeInFile(cart: File, name: String, quantity: Int, price: Float){
        cart.writeText(name + " " + quantity + " "  + price)

    }

}