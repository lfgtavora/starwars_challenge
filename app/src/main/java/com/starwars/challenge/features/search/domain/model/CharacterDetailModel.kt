package com.starwars.challenge.features.search.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDetailModel(
    val name: String,
    val height: String,
    val birthYear: String,
    val filmUrls: List<String>,
    val planetUrl: String,
    val speciesUrls: List<String>,
    val url: String
): Parcelable
