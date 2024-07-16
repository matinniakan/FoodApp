package com.example.foodapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFavoriteBinding
import com.example.foodapp.ui.home.FoodHomeFragmentDirections
import com.example.foodapp.utils.isVisible
import com.example.foodapp.utils.setUpRecyclerView
import com.example.foodapp.viewModels.FoodsFavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    //Binding
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    //other
    private val viewModel:FoodsFavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initViews
        binding.apply {
            //load data
            viewModel.loadFavorites()
            viewModel.favoritesListData.observe(viewLifecycleOwner){
                if (it.isEmpty){
                    emptyLay.isVisible(true,favoriteList)
                    statusLay.disImg.setImageResource(R.drawable.box)
                    statusLay.disTxt.text = getString(R.string.emptyList)
                }else{
                    emptyLay.isVisible(false,favoriteList)
                    favoriteAdapter.setData(it.data!!)
                    favoriteList.setUpRecyclerView(LinearLayoutManager(requireContext()),favoriteAdapter)

                    favoriteAdapter.setOnItemClickListener {  food ->
                        val direction = FavoriteFragmentDirections.actionToDetail(food.id)
                        findNavController().navigate(direction)
                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}