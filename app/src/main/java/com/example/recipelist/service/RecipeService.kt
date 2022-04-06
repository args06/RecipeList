package com.example.recipelist.service

import android.util.Log
import com.example.recipelist.RecipeListener
import com.example.recipelist.model.RecipeResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeService {
    private val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"
    private var retrofit: Retrofit? = null
    fun getApiService(): RecipeAPI? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit?.create(RecipeAPI::class.java)
    }

    fun getData(listener: RecipeListener<List<RecipeResponseItem>>){
        getApiService()?.getRecipeData()?.enqueue(object : Callback<List<RecipeResponseItem>> {
            override fun onResponse(call: Call<List<RecipeResponseItem>>, response: Response<List<RecipeResponseItem>>) {
                val data: List<RecipeResponseItem>? = response.body()
                if (data != null) {
                    listener.onSuccess(data)
                    data.get(0).name?.let { Log.d("Response : ", it) }
                } //kayak return
            }

            override fun onFailure(call: Call<List<RecipeResponseItem>>, t: Throwable) {
                listener.onFailed(t.message)
                t.message?.let { Log.d("Failure", it) }
            }

        })
    }

//    fun getData(listener: RecipeListener<List<RecipeResponseItem>>) {
//        getApiService()?.getRecipeData()?.enqueue(object : Callback<ListRecipeResponse> {
//            override fun onResponse(
//                call: Call<RecipeResponse?>,
//                response: Response<RecipeResponse?>
//            ) {
//                val data: RecipeResponse? = response.body()
//                if (data != null) {
//                    listener.onSuccess(data.recipeResponse) //kayak return
//                }
//            }
//
//            override fun onFailure(call: Call<RecipeResponse?>, t: Throwable) {
//                listener.onFailed(t.message)
//            }
//        })
//    }
}