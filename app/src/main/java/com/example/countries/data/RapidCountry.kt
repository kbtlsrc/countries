package com.example.countries.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RapidCountry(
    val code: String?,
    val name: String?,
    val wikiDataId: String?
):Parcelable{

}
