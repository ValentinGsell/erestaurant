package fr.isen.gsell.erestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import fr.isen.gsell.erestaurant.databinding.ActivityDetailBinding
import fr.isen.gsell.erestaurant.model.Item
import pl.polak.clicknumberpicker.ClickNumberPickerListener
import pl.polak.clicknumberpicker.PickerClickType
import java.io.File

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    var cpt: Int = 0

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


        var value = item.prices[0].price

        binding.add.setOnClickListener {
            cpt+=1
            binding.total.setText(("Total :" + item.prices[0].price.toFloat() * cpt).toString() + "€")
            binding.quantity.setText("Number of items : " + cpt)

        }

        binding.remove.setOnClickListener {
            if(cpt == 0){
                cpt = 0
            }else{
                cpt-=1
                binding.total.setText(("Total :" + item.prices[0].price.toFloat() * cpt).toString() + "€")
                binding.quantity.setText("Number of items : " + cpt)

            }

        }

        binding.total.setOnClickListener {

        }


    }
    fun storeInFile(cart: File, name: String, quantity: Int, price: Float){
        cart.writeText(name + " " + quantity + " "  + price)

    }

}