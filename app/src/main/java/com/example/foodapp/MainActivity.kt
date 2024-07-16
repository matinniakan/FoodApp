package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.foodapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //binding
    private var _binding :ActivityMainBinding ?= null
    private val binding get() = _binding!!

    //other
    private lateinit var  navHost:NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //new controller
        navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        //bottom nav
        binding.bottomNav.setupWithNavController(navHost.navController)
        //show bottom nav
        navHost.navController.addOnDestinationChangedListener{ _,destination,_ ->
            if(destination.id == R.id.foodDetailFragment){
                binding.bottomNav.visibility = View.GONE
            }else{
                binding.bottomNav.visibility = View.VISIBLE
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navHost.navController.navigateUp() || super.onNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}