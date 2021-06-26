package com.azharie.alzaini.moviecatalogueexpert.detail

import androidx.lifecycle.ViewModel
import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie
import com.azharie.alzaini.moviecatalogueexpert.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setMovieFavorite(movie: Movie, newStatus: Boolean){
        return movieUseCase.setFavoriteMovie(movie, newStatus)
    }
}