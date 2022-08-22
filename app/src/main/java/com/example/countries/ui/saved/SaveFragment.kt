package com.example.countries.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.countries.R
import com.example.countries.SaveAction
import com.example.countries.data.RapidCountry
import com.example.countries.databinding.FragmentSaveBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


@AndroidEntryPoint
class SaveFragment: Fragment(R.layout.fragment_save), SaveAdapter.Listener {
    private var binding: FragmentSaveBinding? = null

    @Inject
    lateinit var saveAction: SaveAction
    private val savedAdapter: SaveAdapter by lazy {
        SaveAdapter(this, saveAction)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentSaveBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countries = saveAction.getCountries() ?: arrayListOf()
        if (countries.isEmpty()) {
         rv_countries.visibility = View.GONE

        } else {
            rv_countries.visibility = View.VISIBLE

        }

        saveAction.getSavedCountry()?.observe(viewLifecycleOwner) {
            savedAdapter.setData(saveAction.getCountries() ?: arrayListOf())
            if (countries.isEmpty()) {
                rv_countries.visibility = View.GONE

            } else {
                rv_countries.visibility = View.VISIBLE

            }
        }
        binding?.rvSaved?.apply {
            adapter = savedAdapter
        }
    }

    override fun onClickCountry(country: RapidCountry) {
        countryDetails(country)
    }

    private fun countryDetails(country: RapidCountry) {
        binding?.let {
            Navigation.findNavController(it.rvSaved)
                .navigateUp()
        }
    }

}