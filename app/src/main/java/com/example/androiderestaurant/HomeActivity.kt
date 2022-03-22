package com.example.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.entrees.setOnClickListener{
            goToMenuActivity("Entrées")
        }

        binding.desserts.setOnClickListener {
            goToMenuActivity("Desserts")
        }

        binding.plats.setOnClickListener {
            goToMenuActivity("Plats")
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d("AndroidERestaurant", "Destroyed succesfully")
    }


    private fun goToMenuActivity(category: String)
    {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("categorie", category)
        startActivity(intent)

    }
}