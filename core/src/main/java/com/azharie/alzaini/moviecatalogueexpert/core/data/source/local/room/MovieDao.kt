package com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.room

import androidx.room.*
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM t_movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM t_movies where favorited = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>


    @Transaction
    @Query("SELECT * FROM t_movies WHERE movieId = :movieId")
    fun getDetailMovie(movieId: String): Flow<MovieEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)


    @Update
    fun updateMovie(movie: MovieEntity)


}