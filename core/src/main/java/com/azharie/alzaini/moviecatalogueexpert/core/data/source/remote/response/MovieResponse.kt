package com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("release_date")
    val release: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val movieId: Int,

    @field:SerializedName("vote_count")
    val voteCount: Int
)