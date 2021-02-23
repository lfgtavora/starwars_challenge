package com.starwars.challenge.features.search.presentation.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.starwars.challenge.databinding.ViewHolderSuggestionBinding
import com.starwars.challenge.features.search.domain.model.CharacterModel

class SuggestionViewHolder(
    private val binding: ViewHolderSuggestionBinding,
    private val clickHandler: ClickHandler
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(suggestionItem: CharacterModel) {
        binding.suggestionText.text = suggestionItem.name
        binding.suggestionText.setOnClickListener { clickHandler.onClick(suggestionItem) }
    }

    interface ClickHandler {
        fun onClick(suggestionItem: CharacterModel)
    }
}