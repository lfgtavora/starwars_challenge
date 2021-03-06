package com.starwars.challenge.features.search.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.starwars.challenge.databinding.ActivityMainBinding
import com.starwars.challenge.features.search.data.entity.SuggestionSearchResponse
import com.starwars.challenge.features.search.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTextWatcher()

        observeSuggestionList()

    }

    private fun initTextWatcher() {

        binding.searchBar.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.searchJob?.cancel()
                    viewModel.fetchSearchQuerySuggestions(newText)
                }
                return true
            }

        })
    }

    private fun observeSuggestionList() {
        lifecycleScope.launchWhenCreated {
            viewModel.suggestionList.collect {
                when(it) {
                    is SearchViewModel.SuggestionStates.Success -> {
                        Toast.makeText(this@MainActivity, getName(it.value) , Toast.LENGTH_SHORT).show()
                    }
                    is SearchViewModel.SuggestionStates.Error -> {

                    }
                    is SearchViewModel.SuggestionStates.Loading -> {

                    }
                }
            }
        }
    }

    private fun getName(response: SuggestionSearchResponse): String {
        if (response.results.isNullOrEmpty()) return "Num achei esse cara nao"

        return response.results[0].name
    }


}