package com.azharie.alzaini.moviecatalogueexpert.core.data

import com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.LocalDataSource
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.RemoteDataSource
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.network.ApiResponse
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.response.MovieResponse
import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie
import com.azharie.alzaini.moviecatalogueexpert.core.domain.repository.IMovieDataRepository
import com.azharie.alzaini.moviecatalogueexpert.core.utils.AppExecutors
import com.azharie.alzaini.moviecatalogueexpert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieDataRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDatabase(): Flow<List<Movie>> {
                return localDataSource.getAllMovies()
                    .map { DataMapper.mapEntitiesToDomainMovie(it) }


            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntitiesMovie(data)
                localDataSource.insertMovies(movieList)


            }

        }.asFlow()

    }
    override fun getFavoritedMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomainMovie(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntityMovie(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}