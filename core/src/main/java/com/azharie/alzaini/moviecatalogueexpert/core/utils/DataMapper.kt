package com.azharie.alzaini.moviecatalogueexpert.core.utils

import com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.entity.MovieEntity
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.response.MovieResponse
import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntitiesMovie(input: List<MovieResponse>) : List<MovieEntity>{
        val moviesList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                poster = it.poster,
                release = it.release,
                overview = it.overview,
                vote = it.voteCount,
                favorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomainMovie(input: List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                poster = it.poster,
                release = it.release,
                overview = it.overview,
                vote = it.vote,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntityMovie(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        poster = input.poster,
        release = input.release,
        overview = input.overview,
        vote = input.vote,
        favorite = input.favorite
    )
}