package com.starwars.challenge.features.search.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val name: String,
    val url: String
): Parcelable
