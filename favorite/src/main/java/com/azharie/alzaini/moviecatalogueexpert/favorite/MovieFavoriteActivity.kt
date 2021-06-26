package com.azharie.alzaini.moviecatalogueexpert.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.azharie.alzaini.moviecatalogueexpert.detail.DetailMovieActivity
import com.azharie.alzaini.moviecatalogueexpert.favorite.databinding.ActivityMovieFavoriteBinding
import com.azharie.alzaini.moviecatalogueexpert.core.ui.MovieAdapterFavorite
import com.azharie.alzaini.moviecatalogueexpert.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MovieFavoriteActivity : AppCompatActivity() {
    private lateinit var activityFavoriteMovieBinding: ActivityMovieFavoriteBinding
    private lateinit var adapterFavorite: MovieAdapterFavorite
    private val favoriteViewModel: MovieFavoriteViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavoriteMovieBinding = ActivityMovieFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteMovieBinding.root)

        loadKoinModules(favoriteModule)

        adapterFavorite = MovieAdapterFavorite()
        adapterFavorite.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }
        activityFavoriteMovieBinding.progressBar.visibility = View.VISIBLE

        favoriteViewModel.getFavorites().observe(this, { movies ->
            activityFavoriteMovieBinding.progressBar.visibility = View.GONE
            adapterFavorite.setMoviesFavorite(movies)
            adapterFavorite.notifyDataSetChanged()
        })
        with(activityFavoriteMovieBinding.rvMovieFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapterFavorite
        }
        supportActionBar?.title = "List Favorites"

    }
}