package com.example.countries.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RapidCountryDetail(
    val code: String?,
    val flagImageUri: String?,
    val name: String?,
    val numRegions: Int,
    val wikiDataId: String?
):Parcelable
