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
            var intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("categorie", "Entrees")
            startActivity(intent)
        }

        var platsTextView = findViewById<TextView>(R.id.plats)
        platsTextView.setOnClickListener{
            var intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("categorie", "Plats")
            startActivity(intent)
        }

        var dessertsTextView = findViewById<TextView>(R.id.desserts)
        dessertsTextView.setOnClickListener{
            var intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("categorie", "Desserts")
            startActivity(intent)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AndroidERestaurant", "Destroyed succesfully")
    }

}