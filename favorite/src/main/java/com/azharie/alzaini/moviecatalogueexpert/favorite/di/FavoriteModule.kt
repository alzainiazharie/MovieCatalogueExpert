package com.azharie.alzaini.moviecatalogueexpert.favorite.di
import com.azharie.alzaini.moviecatalogueexpert.favorite.MovieFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
val favoriteModule = module{
    viewModel { MovieFavoriteViewModel(get()) }
}