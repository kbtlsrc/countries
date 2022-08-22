package com.example.countries.ui.saved

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.SaveAction
import com.example.countries.data.RapidCountry
import com.example.countries.databinding.ItemCountryBinding
import com.example.countries.ui.home.HomeAdapter

class SaveAdapter(private val listener: SaveFragment,
                  private val saveAction: SaveAction,
    ) : RecyclerView.Adapter<SaveAdapter.ItemViewHolder>()
    {

        private var savedCountries = saveAction.getCountries() ?: arrayListOf()

        class ItemViewHolder(
            itemView: View,
            private val listener: Listener,
            private val saveAction: SaveAction
        ) : RecyclerView.ViewHolder(itemView) {
             val binding = ItemCountryBinding.bind(itemView)


            fun bind(country: RapidCountry) {
                with(binding) {
                    tvCountry.text = country.name

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false),
            listener,saveAction
        )

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val currentCountry = saveAction.getCountries()?.get(position)
            if (currentCountry != null) {
                holder.bind(currentCountry)
            }
            holder.binding.tvCountry.setOnClickListener {
                listener.onClickCountry(savedCountries[position])

            }
        }

        override fun getItemCount(): Int = saveAction.getCountries()?.size!!

        fun setData(countries: ArrayList<RapidCountry>) {
            this.savedCountries = countries
            notifyDataSetChanged()
        }

        interface Listener {
            fun onClickCountry(country: RapidCountry)
        }

    }
