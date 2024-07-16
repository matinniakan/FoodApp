package com.example.foodapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.database.FoodEntity
import com.example.foodapp.data.model.ResponseFoodsList
import com.example.foodapp.data.repository.FoodDetailRepository
import com.example.foodapp.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(private val repository: FoodDetailRepository) :ViewModel(){

    val foodDetailData = MutableLiveData<MyResponse<ResponseFoodsList>>()
    fun loadFoodDetail(id:Int) = viewModelScope.launch(Dispatchers.IO){
        repository.foodDetail(id).collect{
            foodDetailData.postValue(it)
        }
    }

    fun saveFood(entity: FoodEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.saveFood(entity)
    }

    fun deleteFood(entity: FoodEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteFood(entity)
    }

    val isFavoriteData = MutableLiveData<Boolean>()
    fun existsFood(id:Int) =viewModelScope.launch(Dispatchers.IO){
        repository.existsFood(id).collect{
            isFavoriteData.postValue(it)
        }
    }
}