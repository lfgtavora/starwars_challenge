package com.starwars.challenge.features.search.data.model.response

import com.starwars.challenge.features.search.data.model.CharacterRemoteModel

data class CharacterSearchResponse(
    val results: List<CharacterRemoteModel>
)
