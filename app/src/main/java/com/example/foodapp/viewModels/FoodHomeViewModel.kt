package com.example.foodapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.model.ResponseCategoriesList
import com.example.foodapp.data.model.ResponseFoodsList
import com.example.foodapp.data.repository.FoodsHomeRepository
import com.example.foodapp.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodHomeViewModel @Inject constructor(private val repository:FoodsHomeRepository) :ViewModel() {

    init {
        loadRandomFood()
        loadCategoriesList()
    }

    val randomFoodLiveData = MutableLiveData<List<ResponseFoodsList.Meal>>()

    fun loadRandomFood() =viewModelScope.launch(Dispatchers.IO){
        repository.randomFood().collect(){
            randomFoodLiveData.postValue(it.body()?.meals!!)
        }
    }

    val filtersListLiveData =MutableLiveData<MutableList<Char>>()

    fun loadFiltersList() = viewModelScope.launch(Dispatchers.IO){
        val letters = listOf('A'..'Z').flatten().toMutableList()
        filtersListLiveData.postValue(letters)
    }

    val categoriesListLiveData = MutableLiveData<MyResponse<ResponseCategoriesList>>()

    fun loadCategoriesList() =viewModelScope.launch(Dispatchers.IO){
        repository.categoriesList().collect(){
            categoriesListLiveData.postValue(it)
        }
    }

    val foodsListLiveData = MutableLiveData<MyResponse<ResponseFoodsList>>()

    fun loadFoodsList(letter:String) = viewModelScope.launch(Dispatchers.IO){
        repository.foodsList(letter).collect{
            foodsListLiveData.postValue(it)
        }
    }

    fun loadFFoodBySearch(letter:String) = viewModelScope.launch(Dispatchers.IO){
        repository.foodsBySearch(letter).collect{
            foodsListLiveData.postValue(it)
        }
    }

    fun loadFFoodByCategory(letter:String) = viewModelScope.launch(Dispatchers.IO){
        repository.foodsByCategory(letter).collect{
            foodsListLiveData.postValue(it)
        }
    }
}