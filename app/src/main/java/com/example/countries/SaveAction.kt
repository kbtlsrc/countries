package com.example.countries

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.countries.data.RapidCountry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class SaveAction @Inject constructor(private val preferences: SharedPreferences){
    private var savedCountry: MutableLiveData<Boolean>? = null


    fun getCountries(): ArrayList<RapidCountry>? {
        val gson = Gson()
        val jsonString: String? = preferences.getString("countries", null)
        val type: Type? = object : TypeToken<ArrayList<RapidCountry>>() {}.type

        return gson.fromJson(jsonString, type)
    }

    fun setCountry(country: RapidCountry) {
        val countries = getCountries() ?: arrayListOf()
        countries.add(country)


        savedCountry?.postValue(true)
    }


    fun removeCountry(country: RapidCountry) {
        val countries = getCountries() ?: arrayListOf()
        val requiredCountry = countries.firstOrNull { it.code == country.code }

        if (requiredCountry != null) {
            countries.remove(requiredCountry)
            savedCountry?.postValue(true)
        }

        savedCountry?.postValue(true)
    }

    fun countrySaved(country: RapidCountry): Boolean {
        val countries = getCountries() ?: arrayListOf()

        for (item in countries) {
            if (country.code == item.code) {
                return true
            }
        }
        return false
    }

    fun getSavedCountry(): MutableLiveData<Boolean>? {
        if (savedCountry == null) savedCountry = MutableLiveData<Boolean>()
        return savedCountry
    }
}