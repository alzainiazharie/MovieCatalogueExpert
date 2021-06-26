package com.azharie.alzaini.moviecatalogueexpert.core.domain.usecase

import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie
import com.azharie.alzaini.moviecatalogueexpert.core.domain.repository.IMovieDataRepository

class MovieInteractor(private val movieDataRepository: IMovieDataRepository): MovieUseCase {

    override fun getAllMovies() = movieDataRepository.getAllMovies()

    override fun getFavoriteMovie() =  movieDataRepository.getFavoritedMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return movieDataRepository.setFavoriteMovie(movie, state)
    }

}