package com.example.countries.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.SaveAction
import com.example.countries.api.CountryApi
import com.example.countries.data.RapidCountryDetail
import com.example.countries.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel: ViewModel() {

    private val countryDetail: MutableLiveData<List<RapidCountryDetail>> = TODO()
    val saveAction: SaveAction


    fun getCountryDetail(countryId: String) {
        val request: CountryApi = AppModule.getApiClient().create(CountryApi::class.java)
        val call: Call<List<RapidCountryDetail>> = request.getCountryDetail(countryId)

        call.enqueue(object : Callback<List<RapidCountryDetail>> {
            override fun onResponse(
                call: Call<List<RapidCountryDetail>>,
                response: Response<List<RapidCountryDetail>>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG", "onResponse: ")
                    countryDetail.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<RapidCountryDetail>>, t: Throwable) {
                Log.d("TAG", "onFailure: ");
            }


        })


    }

    fun getCountryDetail(): MutableLiveData<List<RapidCountryDetail>> {
        return countryDetail
    }

}