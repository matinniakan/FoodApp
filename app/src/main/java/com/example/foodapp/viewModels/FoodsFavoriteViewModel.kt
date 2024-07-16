package com.example.foodapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.database.FoodEntity
import com.example.foodapp.data.repository.FoodsFavoriteRepository
import com.example.foodapp.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsFavoriteViewModel @Inject constructor(private val repository: FoodsFavoriteRepository) :ViewModel(){

    val favoritesListData = MutableLiveData<DataStatus<List<FoodEntity>>>()
    fun loadFavorites() =viewModelScope.launch(Dispatchers.IO){
        repository.foodsList().collect{
            favoritesListData.postValue(DataStatus.success(it,it.isEmpty()))
        }
    }
}