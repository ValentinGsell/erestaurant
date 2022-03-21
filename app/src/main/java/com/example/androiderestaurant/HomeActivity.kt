package com.example.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var EntreeButton = findViewById<TextView>(R.id.welcome)
        EntreeButton.setOnClickListener{

        }


        var entreesTextView = findViewById<TextView>(R.id.entrees)
        entreesTextView.setOnClickListener{
            goToNextActivity("Entrees")
        }

        var platsTextView = findViewById<TextView>(R.id.plats)
        platsTextView.setOnClickListener{
            goToNextActivity("Plats")
        }

        var dessertsTextView = findViewById<TextView>(R.id.desserts)
        dessertsTextView.setOnClickListener{
            goToNextActivity("Desserts")

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AndroidERestaurant", "Destroyed succesfully")
    }

    private fun goToNextActivity(category: String){
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("categorie", category)
        startActivity(intent)
    }

}