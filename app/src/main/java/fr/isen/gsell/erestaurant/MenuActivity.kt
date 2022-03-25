package fr.isen.gsell.erestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.gsell.erestaurant.model.APIData
import fr.isen.gsell.erestaurant.model.Item
import com.google.gson.Gson
import fr.isen.gsell.erestaurant.databinding.ActivityMenuBinding
import org.json.JSONObject

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private val itemsList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val param = intent.getStringExtra("categorie").toString()
        //val partTitle = findViewById<TextView>(R.id.categorie)
        //partTitle.text = param

        title = param


        //Set up recycle view
        binding.listItems.layoutManager = LinearLayoutManager(this)
        binding.listItems.adapter = MenuAdapter(itemsList) {}

        loadDataFromServerByCagtegory(param)
    }

    private fun loadDataFromServerByCagtegory(category: String){

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", 1)

        val stringRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {response ->
                val stringResponse = response.toString()
                val item = Gson().fromJson(stringResponse, APIData::class.java)
                fillList(item)
                binding.listItems.adapter = MenuAdapter(itemsList) {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(ITEM_KEY, it)
                    startActivity(intent)
                }
            }, {
                Log.e("MenuActivity", it.message ?: "")
            }
        )
        queue.add(stringRequest)
    }


    companion object{
        const val ITEM_KEY = "item"
    }

    private fun fillList(apiData: APIData){
        if(intent.getStringExtra("categorie") == "Entrées"){
            apiData.data[0].items.forEach{ item: Item -> itemsList.add(item)}
        }
        if(intent.getStringExtra("categorie") == "Plats"){
            apiData.data[1].items.forEach { item: Item -> itemsList.add(item) }
        }
        if(intent.getStringExtra("categorie") == "Desserts"){
            apiData.data[2].items.forEach{ item: Item -> itemsList.add(item)}
        }
        //menuAdapter.notifyDataSetChanged()
    }
}

