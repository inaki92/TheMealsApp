package com.example.themealsapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themealsapp.R
import com.example.themealsapp.databinding.FragmentMealSearchBinding
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.presentation.view.adapter.MealsListAdapter
import com.example.themealsapp.utils.BaseFragment
import com.example.themealsapp.utils.UIState

private const val TAG = "MealSearchFragment"
class MealSearchFragment : BaseFragment() {

    private val binding by lazy {
        FragmentMealSearchBinding.inflate(layoutInflater)
    }

    private val mealsListAdapter by lazy {
        MealsListAdapter {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.rvMealSearch.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = mealsListAdapter
        }
        searchMeals()
        binding.btnSearch.setOnClickListener {
            val searchQuery = binding.etSearch.text.toString()
            mealsViewModel.onSeach(searchQuery)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun searchMeals() {
        mealsViewModel.meals.observe(viewLifecycleOwner) { state ->
            when (state){
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS<List<Meal>> -> {
                    Log.d(TAG, "searchMeals: It brought the reponse to the fragment")
                    mealsListAdapter.updateMeals(state.response)
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {}
                }
            }
        }
    }
}