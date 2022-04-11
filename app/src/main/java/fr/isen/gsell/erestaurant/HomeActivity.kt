package fr.isen.gsell.erestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import fr.isen.gsell.erestaurant.BLEHandler.BLEScanActivity
import fr.isen.gsell.erestaurant.databinding.ActivityHomeBinding
import java.io.File

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

        binding.bluetooth.setOnClickListener {
            goToBTActivity()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        File(cacheDir.absolutePath+"dataPanier.json").writeText("")
    }


    private fun goToMenuActivity(category: String)
    {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("categorie", category)
        startActivity(intent)
    }

    private fun goToBTActivity()
    {
        val intent = Intent(this, BLEScanActivity::class.java)
        startActivity(intent)
    }
}