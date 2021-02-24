package com.starwars.challenge.features.search.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.starwars.challenge.databinding.ViewHolderFilmItemBinding
import com.starwars.challenge.features.search.domain.model.FilmModel
import com.starwars.challenge.features.search.presentation.ui.viewholder.FilmViewHolder

class FilmAdapter : ListAdapter<FilmModel, FilmViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder =
        FilmViewHolder(
            ViewHolderFilmItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<FilmModel>() {
            override fun areItemsTheSame(
                oldItem: FilmModel,
                newItem: FilmModel
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: FilmModel,
                newItem: FilmModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}