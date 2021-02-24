package com.starwars.challenge.features.search.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.starwars.challenge.R
import com.starwars.challenge.databinding.*
import com.starwars.challenge.features.search.domain.model.CharacterDetailModel
import com.starwars.challenge.features.search.domain.model.PlanetModel
import com.starwars.challenge.features.search.domain.model.SpecieModel
import com.starwars.challenge.features.search.presentation.ui.adapter.FilmAdapter
import com.starwars.challenge.features.search.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var profileBinding: LayoutProfileDetailsBinding
    private lateinit var planetBinding: LayoutPlanetDetailsBinding
    private lateinit var speciesBinding: LayoutSpecieDetailsBinding
    private lateinit var filmsBinding: LayoutFilmsListBinding

    private val detailsViewModel: DetailsViewModel by viewModel()
    private val filmAdapter = FilmAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater).apply {
            profileBinding = LayoutProfileDetailsBinding.bind(root)
            planetBinding = LayoutPlanetDetailsBinding.bind(root)
            speciesBinding = LayoutSpecieDetailsBinding.bind(root)
            filmsBinding = LayoutFilmsListBinding.bind(root)
            setContentView(root)
        }
        initView()
        initObservers()
    }

    private fun initView() {
        val extras = intent?.extras ?: return
        val details = extras.getParcelable<CharacterDetailModel>(DETAILS_EXTRA) ?: return

        supportActionBar?.title = details.name

        profileBinding.detailsProfileName.text = getString(R.string.profile_name, details.name)
        profileBinding.detailsProfileBirth.text =
            getString(R.string.birth_profile, details.birthYear)
        profileBinding.detailsProfileHeight.text =
            getString(R.string.height_profile, details.height)

        filmsBinding.detailsFilmsList.adapter = filmAdapter

        detailsViewModel.getPlanet(details.planetUrl)
        detailsViewModel.getSpecies(details.speciesUrls)
        detailsViewModel.getFilms(details.filmUrls)
    }

    private fun initObservers() {
        detailsViewModel.planet.asLiveData().observe(this, Observer {
            when (it) {
                DetailsViewModel.PlanetState.Loading -> {
                    Toast.makeText(this, "Loading Planet", Toast.LENGTH_SHORT).show()
                }
                is DetailsViewModel.PlanetState.Success -> {
                    setupPlanetView(it.value)
                }
                is DetailsViewModel.PlanetState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })

        detailsViewModel.specie.asLiveData().observe(this, Observer {
            when (it) {
                DetailsViewModel.SpecieState.Loading -> {
                    Toast.makeText(this, "Loading Planet", Toast.LENGTH_SHORT).show()
                }
                DetailsViewModel.SpecieState.Empty -> {
                    setupEmptySpecieView()
                }
                is DetailsViewModel.SpecieState.Success -> {
                    setupSpecieView(it.value)
                }
                is DetailsViewModel.SpecieState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })

        detailsViewModel.films.asLiveData().observe(this, Observer {
            when (it) {
                DetailsViewModel.FilmState.Loading -> {
                    Toast.makeText(this, "Loading Planet", Toast.LENGTH_SHORT).show()
                }
                DetailsViewModel.FilmState.Empty -> {
//                    setupEmptySpecieView()
                }
                is DetailsViewModel.FilmState.Success -> {
                    filmAdapter.submitList(it.value)
                }
                is DetailsViewModel.FilmState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })


    }

    private fun setupSpecieView(value: SpecieModel) =
        speciesBinding.run {
            detailsSpeciesName.text = getString(R.string.species_name, value.name)
            detailsSpeciesHomeworld.text = getString(R.string.species_homeworld, value.homeWorld)
            detailsSpeciesLanguage.text = getString(R.string.species_language, value.language)
        }

    private fun setupEmptySpecieView() =
        speciesBinding.run {
            detailsSpeciesName.isVisible = false
            detailsSpeciesHomeworld.isVisible = false
            detailsSpeciesLanguage.isVisible = false
            detailsSpeciesEmpty.isVisible = true
        }

    private fun setupPlanetView(planet: PlanetModel) {
        planetBinding.detailsPlanetName.text = getString(R.string.planet_name, planet.name)
        planetBinding.detailsPlanetPopulation.text =
            getString(R.string.planet_population, planet.population)
    }

    companion object {
        private const val DETAILS_EXTRA = "details_extra"

        fun getIntent(
            context: Context,
            characterDetails: CharacterDetailModel
        ) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtras(
                    bundleOf(
                        DETAILS_EXTRA to characterDetails
                    )
                )
            }
    }
}