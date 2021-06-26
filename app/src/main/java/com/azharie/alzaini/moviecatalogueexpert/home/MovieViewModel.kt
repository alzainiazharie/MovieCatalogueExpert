package com.azharie.alzaini.moviecatalogueexpert.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azharie.alzaini.moviecatalogueexpert.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovies()  = movieUseCase.getAllMovies().asLiveData()
}