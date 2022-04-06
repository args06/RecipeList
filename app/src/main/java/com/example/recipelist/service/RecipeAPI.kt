package com.example.recipelist.service

import com.example.recipelist.model.RecipeResponse
import com.example.recipelist.model.RecipeResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeAPI {

    @GET("recipes.json")
    fun getRecipeData(): Call<List<RecipeResponseItem>>

}