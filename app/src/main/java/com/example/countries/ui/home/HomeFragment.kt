package com.example.countries.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.countries.R
import com.example.countries.data.RapidCountry
import com.example.countries.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var _binding: FragmentHomeBinding

    private val binding get() = _binding!!
    private val adapter: HomeAdapter = TODO()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        binding.apply {
            rvCountries.setHasFixedSize(true)
            rvCountries.adapter = adapter

        }
        initObservers()
        observeCountryName()

    }

    private fun initObservers() {
        viewModel.getCountryList().observe(viewLifecycleOwner
        ) { countries ->
            Log.d("TAG", "onChanged: ")
            prepareRecycler(countries)
        }

    }



    private fun prepareRecycler(countries: List<RapidCountry>) {
        adapter.setAdapterList(countries)
    }

 fun observeCountryName() {
        viewModel.getCountryName()
    }

}