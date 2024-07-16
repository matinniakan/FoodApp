package com.example.foodapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.databinding.FragmentFoodHomeBinding
import com.example.foodapp.ui.home.adapters.CategoriesAdapter
import com.example.foodapp.ui.home.adapters.FoodsAdapter
import com.example.foodapp.utils.CheckConnection
import com.example.foodapp.utils.MyResponse
import com.example.foodapp.utils.isVisible
import com.example.foodapp.utils.setUpListWithAdapter
import com.example.foodapp.utils.setUpRecyclerView
import com.example.foodapp.viewModels.FoodHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodHomeFragment : Fragment() {
    //binding
    private var _binding : FragmentFoodHomeBinding ?= null
    private val binding get() = _binding

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    @Inject
    lateinit var connection:CheckConnection

    //other
    private val viewModel:FoodHomeViewModel by viewModels()
    enum class PageState{ EMPTY, NETWORK, NONE}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding =FragmentFoodHomeBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initViews
        binding?.apply {
            //random food
            //viewModel.loadRandomFood()
            viewModel.randomFoodLiveData.observe(viewLifecycleOwner){
                it[0].let { meal ->
                    headerImg.load(meal.strMealThumb){
                        crossfade(true)
                        crossfade(500)
                    }
                }
            }
            //filters
            viewModel.loadFiltersList()
            viewModel.filtersListLiveData.observe(viewLifecycleOwner){
                filterSpinner.setUpListWithAdapter(it){ letter ->
                    viewModel.loadFoodsList(letter)
                }
            }
            //categories
            //viewModel.loadCategoriesList()
            viewModel.categoriesListLiveData.observe(viewLifecycleOwner){
                when(it.status){
                    MyResponse.Status.LOADING ->{
                        homeCategoryLoading.isVisible(true,categoryList)
                    }
                    MyResponse.Status.SUCCESS ->{
                        homeCategoryLoading.isVisible(false,categoryList)
                        categoriesAdapter.setData(it.data!!.categories)
                        categoryList.setUpRecyclerView(
                            LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false),
                            categoriesAdapter
                        )
                    }
                    MyResponse.Status.ERROR ->{
                        homeCategoryLoading.isVisible(false,categoryList)
                    }
                }
            }
            categoriesAdapter.setOnItemClickListener {
                viewModel.loadFFoodByCategory(it.strCategory.toString())
            }
            //foods
            viewModel.loadFoodsList("A")
            viewModel.foodsListLiveData.observe(viewLifecycleOwner){
                when(it.status){
                    MyResponse.Status.LOADING ->{
                        homeFoodsLoading.isVisible(true,foodsList)
                    }
                    MyResponse.Status.SUCCESS ->{
                        homeFoodsLoading.isVisible(false,foodsList)
                        if (it.data!!.meals !=null){
                            if (it.data.meals!!.isNotEmpty()){
                                checkConnectionOrEmpty(false,PageState.NONE)
                                foodsAdapter.setData(it.data!!.meals!!)
                                foodsList.setUpRecyclerView(
                                    LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false),
                                    foodsAdapter
                                )
                            }
                        }else{
                            checkConnectionOrEmpty(true,PageState.EMPTY)
                        }
                    }
                    MyResponse.Status.ERROR ->{
                        homeFoodsLoading.isVisible(false,foodsList)
                    }
                }
            }
            foodsAdapter.setOnItemClickListener {
                val direction = FoodHomeFragmentDirections.actionToDetail(it.idMeal!!.toInt())
                findNavController().navigate(direction)
            }
            //search
            searchEdt.addTextChangedListener {
                if(it.toString().length>2){
                    viewModel.loadFFoodBySearch(it.toString())
                }
            }
           //internet
            connection.observe(viewLifecycleOwner){
                if (it) {
                    checkConnectionOrEmpty(false,PageState.NONE)
                }else{
                    checkConnectionOrEmpty(true,PageState.NETWORK)
                }
            }
        }
    }

    private fun checkConnectionOrEmpty(isShownError:Boolean,state:PageState){
        binding?.apply {
            if(isShownError){
                homeDisLay.isVisible(true,homeContent)
                when(state){
                    PageState.EMPTY ->{
                        disconnectLay.disImg.setImageResource(R.drawable.box)
                        disconnectLay.disTxt.text =getString(R.string.emptyList)
                    }
                    PageState.NETWORK ->{
                        disconnectLay.disImg.setImageResource(R.drawable.disconnect)
                        disconnectLay.disTxt.text =getString(R.string.checkInternet)
                    }
                    else ->{}
                }
            }else{
                homeDisLay.isVisible(false,homeContent)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }

}