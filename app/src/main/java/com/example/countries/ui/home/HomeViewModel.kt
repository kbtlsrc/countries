package com.example.countries.ui.home


import android.util.Log
import androidx.lifecycle.*
import com.example.countries.api.CountryApi
import com.example.countries.data.RapidCountry
import com.example.countries.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel: ViewModel() {

    private val countryList: MutableLiveData<List<RapidCountry>> = TODO()


     fun getCountryName(){
        val request: CountryApi = AppModule.getApiClient().create(CountryApi::class.java)
        val call: Call<List<RapidCountry>> = request.getCountryList(10)

        call.enqueue(object : Callback<List<RapidCountry>> {
            override fun onResponse(
                call: Call<List<RapidCountry>>,
                response: Response<List<RapidCountry>>
            ) {
                if(response.isSuccessful){
                    Log.d("TAG", "onResponse: ")
                    countryList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<RapidCountry>>, t: Throwable) {
                Log.d("TAG", "onFailure: ");
            }


        })


    }
    fun getCountryList(): LiveData<List<RapidCountry>> {
        return countryList
    }


}