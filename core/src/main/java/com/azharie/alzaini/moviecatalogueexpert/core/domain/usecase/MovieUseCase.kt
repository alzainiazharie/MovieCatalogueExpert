package com.azharie.alzaini.moviecatalogueexpert.core.domain.usecase

import com.azharie.alzaini.moviecatalogueexpert.core.data.Resource
import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state:Boolean)
}