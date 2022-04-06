package com.example.recipelist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelist.adapter.RecipeAdapter
import com.example.recipelist.model.RecipeResponseItem
import com.example.recipelist.service.RecipeService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RecipeService().getData(recipeListener)

    }

    private var recipeListener: RecipeListener<List<RecipeResponseItem>> =
        object : RecipeListener<List<RecipeResponseItem>> {
            override fun onSuccess(items: List<RecipeResponseItem>) {

                val linearLayoutManager = LinearLayoutManager(applicationContext)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                showRecyclerView(items)
                for (i in items.indices) {
                    items[i].name.let {
                        if (it != null) {
                            Log.d("Hasil : ", it)
                        }
                    }
                }
            }

            override fun onFailed(msg: String?) {
                if (msg != null) {
                    Log.d("ISI ERROR", msg)
                }
            }
        }

    private fun showRecyclerView(recipe: List<RecipeResponseItem>){
        val rvRecyclerView = findViewById<RecyclerView>(R.id.rv_content)
        rvRecyclerView.setHasFixedSize(true)

        rvRecyclerView.layoutManager = LinearLayoutManager(this)
        val listRecipeAdapter = RecipeAdapter(recipe)
        rvRecyclerView.adapter = listRecipeAdapter
    }
}