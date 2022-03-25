package fr.isen.gsell.erestaurant.model

data class Category(
    var name_fr: String,
    var name_en: String,
    var items: ArrayList<Item>
)
