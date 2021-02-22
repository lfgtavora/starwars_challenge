package com.starwars.challenge.features.search.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.starwars.challenge.databinding.ActivityMainBinding
import com.starwars.challenge.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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
                }
                is SearchViewModel.SuggestionStates.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}