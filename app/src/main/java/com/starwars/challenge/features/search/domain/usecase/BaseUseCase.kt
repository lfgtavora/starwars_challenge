package com.starwars.challenge.features.search.domain.usecase

interface BaseUseCase<P, R> {
    fun execute(params: P): R
}