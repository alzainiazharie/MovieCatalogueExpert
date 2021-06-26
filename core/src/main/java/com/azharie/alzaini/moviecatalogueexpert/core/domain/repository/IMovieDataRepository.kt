package com.azharie.alzaini.moviecatalogueexpert.core.domain.repository

import com.azharie.alzaini.moviecatalogueexpert.core.data.Resource
import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieDataRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoritedMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)


}