package com.example.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var param = intent.getStringExtra("categorie")
        var setCategorie = findViewById<TextView>(R.id.categorie).setText(param)
        if(param == "Desserts"){
            val array = resources.getStringArray(R.array.Desserts).toList()
            val recycler = findViewById<RecyclerView>(R.id.list_items)
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = param?.let { MenuAdapter(array as ArrayList<String>) }
        }
        else if(param == "Plats")
        {
            val array = resources.getStringArray(R.array.Plats).toList()
            val recycler = findViewById<RecyclerView>(R.id.list_items)
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = param?.let { MenuAdapter(array as ArrayList<String>) }
        }
        else if(param == "Entrees")
        {
            val array = resources.getStringArray(R.array.Entrees).toList()
            val recycler = findViewById<RecyclerView>(R.id.list_items)
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = param?.let { MenuAdapter(array as ArrayList<String>) }
        }
        /*val recyclerView: RecyclerView = binding.recyclerView
        * menuAdapter = MenuAdapter(itemsList)
        * val layoutManager = LinearLayoutManager(applicationContext)
        * recyclerView.layoutManager = layoutManager
        * recyclerView.adapter = customAdapter*/

    }
}
