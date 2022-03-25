package com.example.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.helper.widget.Carousel
import com.example.androiderestaurant.databinding.ActivityDetailBinding
import com.example.androiderestaurant.model.Item

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

        val cartButton: Button = findViewById<Button>(R.id.addToCartButton)
        val addButton: Button = findViewById(R.id.add)
        var value = item.prices[0].price

        addButton.setOnClickListener {
            cpt+=1
            cartButton.setText(item.prices[0].price.toInt()*cpt)
        }

        val removeButton: Button = findViewById(R.id.remove)
        removeButton.setOnClickListener{
            cpt-=1
            cartButton.setText(item.prices[0].price.toInt()*cpt)

        }


    }
}