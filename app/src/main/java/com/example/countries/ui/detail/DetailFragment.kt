package com.example.countries.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.countries.R
import com.example.countries.data.RapidCountry
import com.example.countries.databinding.FragmentDetailBinding
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.ui.home.HomeAdapter
import com.example.countries.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment: Fragment(R.layout.fragment_detail) {
    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var _binding: FragmentDetailBinding

    private var country: RapidCountry? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding.btnSave.setOnClickListener {
            country?.let {
                val saved = viewModel.saveAction.countrySaved(it)
                if (saved)
                    viewModel.saveAction.removeCountry(it)
                else
                    viewModel.saveAction.setCountry(it)
            }
        }
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        country = arguments?.getParcelable("countries")
        country?.let {
            val saved = viewModel.saveAction.countrySaved(it)
            if (saved) {
                _binding?.btnSave.setImageResource(R.drawable.ic_full_star)
            } else {
                _binding?.btnSave.setImageResource(R.drawable.ic_star)
            }
        }

        getCountryDetail()

    }

    private fun getCountryDetail() {
        viewModel.saveAction.getSavedCountry()?.observe(viewLifecycleOwner){

        }
    }


    private fun observeViewModel() {
        viewModel.saveAction.getCountries()
    }



}