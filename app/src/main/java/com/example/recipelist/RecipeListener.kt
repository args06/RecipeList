package com.example.recipelist

interface RecipeListener<T> {
    fun onSuccess(items: T)
    fun onFailed(msg: String?)
}