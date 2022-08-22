package com.example.countries.api


import com.example.countries.data.RapidCountry
import com.example.countries.data.RapidCountryDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApi {

@GET("countries")
 fun getCountryList(
    @Query("limit") limit: Int
): Call<List<RapidCountry>>

    @GET("countries/{countryId}")
    fun getCountryDetail(
        @Path("countryId") countryId: String,
    ): Call<List<RapidCountryDetail>>
}
