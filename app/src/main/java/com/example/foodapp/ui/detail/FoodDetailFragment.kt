package com.example.foodapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.foodapp.R
import com.example.foodapp.data.database.FoodEntity
import com.example.foodapp.databinding.FragmentFoodDetailBinding
import com.example.foodapp.databinding.FragmentFoodHomeBinding
import com.example.foodapp.ui.detail.player.PlayerActivity
import com.example.foodapp.ui.home.FoodHomeFragment
import com.example.foodapp.utils.CheckConnection
import com.example.foodapp.utils.MyResponse
import com.example.foodapp.utils.VIDEO_ID
import com.example.foodapp.utils.isVisible
import com.example.foodapp.utils.setUpRecyclerView
import com.example.foodapp.viewModels.FoodDetailViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    //binding
    private var _binding : FragmentFoodDetailBinding?= null
    private val binding get() = _binding

    @Inject
    lateinit var connection: CheckConnection

    @Inject
    lateinit var entity: FoodEntity

    //other
    private val args:FoodDetailFragmentArgs by navArgs()
    private var foodID = 0
    private val viewModel:FoodDetailViewModel by viewModels()
    private var isFavorite = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFoodDetailBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initViews
        binding?.apply {
            //get data
            foodID =args.foodId
            //back
            detailBack.setOnClickListener{ findNavController().navigateUp()}
            //call api
            viewModel.loadFoodDetail(foodID)
            viewModel.foodDetailData.observe(viewLifecycleOwner){
                when(it.status){
                    MyResponse.Status.LOADING ->{
                        detailLoading.isVisible(true,detailContentLay)
                    }
                    MyResponse.Status.SUCCESS ->{
                        detailLoading.isVisible(false,detailContentLay)
                        it.data?.meals?.get(0)?.let { itMeal ->
                            //entity
                            entity.id = itMeal.idMeal!!.toInt()
                            entity.title = itMeal.strMeal.toString()
                            entity.img = itMeal.strMealThumb.toString()
                            //set data
                            foodCoverImg.load(itMeal.strMealThumb){
                                crossfade(true)
                                crossfade(500)
                            }
                            foodCategoryTxt.text=itMeal.strCategory
                            foodAreaTxt.text=itMeal.strArea
                            foodTitleTxt.text=itMeal.strMeal
                            foodDescTxt.text=itMeal.strInstructions
                            //play
                            if(itMeal.strYoutube !=null){
                                foodPlayImg.visibility = View.VISIBLE
                                foodPlayImg.setOnClickListener {
                                    val videoId = itMeal.strYoutube.split("=")[1]
                                    Intent(requireContext(),PlayerActivity::class.java).also {
                                        it.putExtra(VIDEO_ID,videoId)
                                        startActivity(it)
                                    }
                                }
                            }else{
                                foodPlayImg.visibility = View.GONE
                            }
                            //source
                            if(itMeal.strSource !=null){
                                foodSourceImg.visibility = View.VISIBLE
                                foodSourceImg.setOnClickListener {
                                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(itMeal.strSource)))
                                }
                            }else{
                                foodSourceImg.visibility = View.GONE
                            }
                        }
                        //Json Array
                        val jsonData = JSONObject(Gson().toJson(it.data))
                        val meals =jsonData.getJSONArray("meals")
                        val meal =meals.getJSONObject(0)
                        //Ingredient
                        for(i in 1..15){
                            val ingredient= meal.getString("strIngredient$i")
                            if (ingredient.isNullOrEmpty().not()){
                                ingredientsTxt.append("$ingredient\n")
                            }
                        }
                        //Measure
                        for(i in 1..15){
                            val measure= meal.getString("strMeasure$i")
                            if (measure.isNullOrEmpty().not()){
                                measureTxt.append("$measure\n")
                            }
                        }
                    }
                    MyResponse.Status.ERROR ->{
                        detailLoading.isVisible(false,detailContentLay)
                    }
                }
            }
            //favorite
            viewModel.existsFood(foodID)
            viewModel.isFavoriteData.observe(viewLifecycleOwner){
                isFavorite = it
                if (it){
                    detailFav.setColorFilter(ContextCompat.getColor(requireContext(),R.color.tartOrange))
                }else{
                    detailFav.setColorFilter(ContextCompat.getColor(requireContext(),R.color.black))
                }
            }
            //save & delete
            detailFav.setOnClickListener {
                if (isFavorite){
                    viewModel.deleteFood(entity)
                }else{
                    viewModel.saveFood(entity)
                }
            }
            //internet
            connection.observe(viewLifecycleOwner){
                if (it) {
                    checkConnectionOrEmpty(false, FoodHomeFragment.PageState.NONE)
                }else{
                    checkConnectionOrEmpty(true, FoodHomeFragment.PageState.NETWORK)
                }
            }

        }
    }

    private fun checkConnectionOrEmpty(isShownError:Boolean,state: FoodHomeFragment.PageState){
        binding?.apply {
            if(isShownError){
                homeDisLay.isVisible(true,detailContentLay)
                when(state){
                    FoodHomeFragment.PageState.EMPTY ->{
                        disconnectLay.disImg.setImageResource(R.drawable.box)
                        disconnectLay.disTxt.text =getString(R.string.emptyList)
                    }
                    FoodHomeFragment.PageState.NETWORK ->{
                        disconnectLay.disImg.setImageResource(R.drawable.disconnect)
                        disconnectLay.disTxt.text =getString(R.string.checkInternet)
                    }
                    else ->{}
                }
            }else{
                homeDisLay.isVisible(false,detailContentLay)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }

}