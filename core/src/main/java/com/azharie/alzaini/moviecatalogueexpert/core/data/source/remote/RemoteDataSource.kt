package com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote

import android.util.Log
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.network.ApiResponse
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.network.ApiService
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){
    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {

        return flow {
            try {
                val responseApi = apiService.getListPopularMovies(3)
                val data = responseApi.results
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(responseApi.results))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RDS", e.toString())
            }
        }.flowOn(Dispatchers.IO) 

    }
}
