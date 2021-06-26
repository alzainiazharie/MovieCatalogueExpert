package com.azharie.alzaini.moviecatalogueexpert.core.data.source.local

import com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.entity.MovieEntity
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao){
    fun getAllMovies(): Flow<List<MovieEntity>> = mMovieDao.getMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = mMovieDao.getFavoriteMovie()

    suspend fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.favorite = newState
        mMovieDao.updateMovie(movie)
    }
}