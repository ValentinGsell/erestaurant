package com.example.androiderestaurant.model

import java.io.Serializable

data class Item(
    val id: String,
    val name_fr: String,
    val name_en: String,
    val id_category: String,
    val categ_name_fr: String,
    val categ_name_en: String,
    val images: ArrayList<String>,
    val ingredients: ArrayList<Ingredient>,
    val prices: ArrayList<Price>
): Serializable
