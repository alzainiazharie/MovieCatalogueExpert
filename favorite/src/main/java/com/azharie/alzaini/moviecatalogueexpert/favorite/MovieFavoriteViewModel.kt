package com.azharie.alzaini.moviecatalogueexpert.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azharie.alzaini.moviecatalogueexpert.core.domain.usecase.MovieUseCase

class MovieFavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getFavorites() = movieUseCase.getFavoriteMovie().asLiveData()
}