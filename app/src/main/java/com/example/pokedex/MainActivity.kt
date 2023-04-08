package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers


import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var pokeList: MutableList<Triple<String, String, String>>
    private lateinit var rvPoke: RecyclerView
    var pokeImageURL = ""
    var pokeNameURL = ""
    var pokeAbility = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.get(this@MainActivity)

        rvPoke = findViewById(R.id.pokeList)
        pokeList = mutableListOf()
        for (i in 0 until 20)
        {
            getPokeImageURL()
        }

        Log.d("petImageURL", "pet image URL set")


        /*val imageView = findViewById<ImageView>(R.id.image)
        val charId = findViewById<TextView>(R.id.charId)
        val name = findViewById<TextView>(R.id.name)*/


    }

    private fun getPokeImageURL() {
        val client = AsyncHttpClient()
        val pokeRandom = Random.nextInt(1281)

        client["https://pokeapi.co/api/v2/pokemon/$pokeRandom", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                pokeImageURL = json.jsonObject.getJSONObject("sprites").getString("front_default")

                Log.d("poke success", "response successful$json")

                pokeNameURL = json.jsonObject.getString("name").replaceFirstChar { it.titlecase() }
                Log.d("Poke Name", "response success")

                pokeAbility = json.jsonObject.getJSONArray("abilities").getJSONObject(0)
                    .getJSONObject("ability").getString("name").replaceFirstChar { it.titlecase() }
                Log.d("Poke Height", "response success")

                pokeList.add(Triple(pokeImageURL, pokeNameURL, pokeAbility))

                val adapter = PokeAdapter(pokeList)
                rvPoke.adapter = adapter
                rvPoke.layoutManager = LinearLayoutManager(this@MainActivity)
                //var itemDecoration = DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL)
                rvPoke.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))
                //getDrawable(R.drawable.divider)?.let { itemDecoration.setDrawable(it) }
                //rvPoke.addItemDecoration(itemDecoration)


            }


            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("poke Error", errorResponse)
            }
        }] //end of client

    } //end of getPokeImageURL()
}







