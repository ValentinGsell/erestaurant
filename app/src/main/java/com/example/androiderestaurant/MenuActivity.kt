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

        var getCategorie = getIntent().getStringExtra("categorie").toString()
        var setCategorie = findViewById<TextView>(R.id.categorie).setText(getCategorie)

        val recycler = findViewById<RecyclerView>(R.id.list_item)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = MenuAdapter(arrayListOf<String>())
    }
}