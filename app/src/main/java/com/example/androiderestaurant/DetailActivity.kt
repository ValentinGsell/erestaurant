package com.example.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiderestaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}