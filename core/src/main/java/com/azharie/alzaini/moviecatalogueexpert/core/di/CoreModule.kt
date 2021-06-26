package com.azharie.alzaini.moviecatalogueexpert.core.di

import androidx.room.Room
import com.azharie.alzaini.moviecatalogueexpert.core.data.MovieDataRepository
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.LocalDataSource
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.local.room.MovieDatabase
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.RemoteDataSource
import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.network.ApiService
import com.azharie.alzaini.moviecatalogueexpert.core.domain.repository.IMovieDataRepository
import com.azharie.alzaini.moviecatalogueexpert.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dbModule = module {
    factory { get<MovieDatabase>().movieTvShowDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "MovieCatalogue.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieDataRepository> { MovieDataRepository(get(), get(), get()) }

}

