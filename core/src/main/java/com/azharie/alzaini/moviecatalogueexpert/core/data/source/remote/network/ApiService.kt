package com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.network

import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.response.ListMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object{
        const val API_KEY = "a162d32c91ae5973f6a11863f1331df6"
    }
    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getListPopularMovies(
        @Query("page") position: Int
    ): ListMoviesResponse


}