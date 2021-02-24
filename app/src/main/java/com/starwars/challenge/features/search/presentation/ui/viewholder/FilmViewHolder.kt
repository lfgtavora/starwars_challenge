package com.starwars.challenge.features.search.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.starwars.challenge.databinding.ViewHolderFilmItemBinding
import com.starwars.challenge.features.search.domain.model.FilmModel

class FilmViewHolder(private val binding: ViewHolderFilmItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(filmItem: FilmModel) {
        binding.filmItemTitle.text = filmItem.title
        binding.filmItemPresentation.text = filmItem.openingCrawl
    }
}