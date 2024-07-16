package com.example.foodapp.data.repository

import com.example.foodapp.data.database.FoodDao
import javax.inject.Inject

class FoodsFavoriteRepository @Inject constructor(private val dao:FoodDao) {
    fun foodsList() = dao.getAllFoods()
}