package com.starwars.challenge.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starwars.challenge.features.search.domain.model.PlanetModel
import com.starwars.challenge.features.search.domain.model.SpecieModel
import com.starwars.challenge.features.search.domain.usecase.IPlanetUseCase
import com.starwars.challenge.features.search.domain.usecase.ISpecieUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class DetailsViewModel(
    private val planetUseCase: IPlanetUseCase,
    private val specieUseCase: ISpecieUseCase
) : ViewModel() {

    private val _planet = MutableStateFlow<PlanetState>(PlanetState.Initial)
    val planet: StateFlow<PlanetState>
        get() = _planet

    private val _specie = MutableStateFlow<SpecieState>(SpecieState.Initial)
    val specie: StateFlow<SpecieState>
        get() = _specie

    fun getPlanet(url: String) {
        viewModelScope.launch {
            planetUseCase.execute(url)
                .catch {
                    _planet.value = PlanetState.Error(it.message ?: "Error Planet")
                }
                .collect {
                    _planet.value = PlanetState.Success(it)
                }
        }
    }

    fun getSpecies(speciesUrls: List<String>) {
        if (speciesUrls.isNullOrEmpty()) {
            _specie.value = SpecieState.Empty
            return
        }

        viewModelScope.launch {
            specieUseCase.execute(speciesUrls.first())
                .catch { _specie.value = SpecieState.Error(it.message ?: "Error Specie") }
                .collect { _specie.value = SpecieState.Success(it) }
        }
    }

//    .flatMapMerge { specieUseCase.execute(it) }
//    .collect { list.add(it) }

    sealed class PlanetState {
        object Initial : PlanetState()
        object Loading : PlanetState()
        data class Success(val value: PlanetModel) : PlanetState()
        data class Error(val error: String) : PlanetState()
    }

    sealed class SpecieState {
        object Initial : SpecieState()
        object Loading : SpecieState()
        object Empty : SpecieState()
        data class Success(val value: SpecieModel) : SpecieState()
        data class Error(val error: String) : SpecieState()
    }
}