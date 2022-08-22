package com.example.countries.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.data.RapidCountry
import com.example.countries.databinding.ItemCountryBinding


class HomeAdapter: RecyclerView.Adapter<HomeAdapter.CountryViewHolder>() {
    private var list: ArrayList<RapidCountry> = ArrayList()
    private var itemClickListener: ItemClickListener? = null


    fun setAdapterList(countries : List<RapidCountry>){
       list.addAll(countries)
        this.notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return  CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country: RapidCountry = list[position]

        if(country != null){
            holder.bind(country)
        }

        /*    if(!TextUtils.isEmpty((country.name))){
                binding?.tvCountry?.text = country.name
            }
     */
    }


    interface ItemClickListener {
        fun onClick(pos: Int, country: RapidCountry)
    }

    fun setOnClickListener(itemClickListener: ItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    private fun getItem(pos: Int): RapidCountry? {
        return list.get(pos)
    }

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener{
        private var list: ArrayList<RapidCountry> = ArrayList()

        private var itemClickListener: ItemClickListener? = null


            fun bind(country: RapidCountry){
                binding.apply {
                    tvCountry.text = country.name
                }
            }


        override fun onClick(view: View?) {
            itemClickListener?.onClick(adapterPosition, list.get(adapterPosition))
        }


    }


    override fun getItemCount(): Int = list.size

}