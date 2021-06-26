package com.azharie.alzaini.moviecatalogueexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val poster: String,
    val release: String,
    val overview: String,
    val vote: Int,
    val favorite: Boolean = false
) : Parcelable