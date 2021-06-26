package com.azharie.alzaini.moviecatalogueexpert.di

import com.azharie.alzaini.moviecatalogueexpert.detail.DetailMovieViewModel
import com.azharie.alzaini.moviecatalogueexpert.core.domain.usecase.MovieInteractor
import com.azharie.alzaini.moviecatalogueexpert.core.domain.usecase.MovieUseCase
import com.azharie.alzaini.moviecatalogueexpert.home.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase>{ MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}