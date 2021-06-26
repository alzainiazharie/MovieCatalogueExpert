package com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_movies")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "release")
    var release: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "vote")
    var vote: Int,

    @ColumnInfo(name = "favorited")
    var favorite: Boolean = false
)
