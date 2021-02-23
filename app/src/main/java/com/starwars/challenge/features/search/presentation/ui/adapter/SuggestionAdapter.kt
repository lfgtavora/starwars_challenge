package com.starwars.challenge.features.search.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.starwars.challenge.databinding.ViewHolderSuggestionBinding
import com.starwars.challenge.features.search.domain.model.CharacterModel
import com.starwars.challenge.features.search.presentation.ui.viewholder.SuggestionViewHolder

class SuggestionAdapter : ListAdapter<CharacterModel, SuggestionViewHolder>(diffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder =
        SuggestionViewHolder(
            ViewHolderSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
                override fun areItemsTheSame(
                    oldItem: CharacterModel,
                    newItem: CharacterModel
                ): Boolean {
                    return oldItem.url == newItem.url
                }

                override fun areContentsTheSame(
                    oldItem: CharacterModel,
                    newItem: CharacterModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}