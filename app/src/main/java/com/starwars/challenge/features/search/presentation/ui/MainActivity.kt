package com.starwars.challenge.features.search.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.starwars.challenge.databinding.ActivityMainBinding
import com.starwars.challenge.features.search.domain.model.CharacterModel
import com.starwars.challenge.features.search.presentation.ui.adapter.SuggestionAdapter
import com.starwars.challenge.features.search.presentation.ui.viewholder.SuggestionViewHolder
import com.starwars.challenge.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SuggestionViewHolder.ClickHandler {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SearchViewModel by viewModel()

    private val suggestionAdapter = SuggestionAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTextWatcher()
        setupView()
        setupObservers()
    }

    private fun setupView() {
        binding.suggestionList.adapter = suggestionAdapter
    }

    private fun setupObservers() {
        observeSuggestionList()
        observeCharacterDetails()
    }

    private fun initTextWatcher() {

        binding.searchBar.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.fetchSearchQuerySuggestions(newText)
                }
                return true
            }
        })
    }

    private fun observeSuggestionList() {
        viewModel.suggestionList2.asLiveData().observe(this, Observer {
            when (it) {
                SearchViewModel.SuggestionStates.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is SearchViewModel.SuggestionStates.Success -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    if (it.value.isNotEmpty()) showSuggestionList(it.value) else hideSuggestionList()
                }
                is SearchViewModel.SuggestionStates.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun observeCharacterDetails() {
        viewModel.characterDetails.asLiveData().observe(this, Observer {
            when (it) {
                SearchViewModel.DetailsStates.Loading -> {
                    Toast.makeText(this, "Loading Details", Toast.LENGTH_SHORT).show()
                }
                is SearchViewModel.DetailsStates.Success -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
//                    startActivity(DetailsActivity.getIntent(this, it.value, searchedCharacter!!))
                    startActivity(DetailsActivity.getIntent(this, it.value))
                }
                is SearchViewModel.DetailsStates.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showSuggestionList(suggestionList: List<CharacterModel>) {
        suggestionAdapter.submitList(suggestionList)
        binding.suggestionList.isVisible = true
    }

    private fun hideSuggestionList() {
        binding.suggestionList.isVisible = false
    }

    override fun onClick(suggestionItem: CharacterModel) {
//        searchedCharacter = suggestionItem
        viewModel.fetchDetails(suggestionItem.url)
    }
}