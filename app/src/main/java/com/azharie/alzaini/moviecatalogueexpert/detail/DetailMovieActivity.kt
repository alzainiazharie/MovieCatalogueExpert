package com.azharie.alzaini.moviecatalogueexpert.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.azharie.alzaini.moviecatalogueexpert.R
import com.azharie.alzaini.moviecatalogueexpert.databinding.ActivityDetailMovieBinding
import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val detailViewModel : DetailMovieViewModel by viewModel()

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        populateMovie(detailMovie)
        supportActionBar?.title = resources.getString(R.string.detail_movie)

    }

    private fun populateMovie(detailMovie: Movie?) {
        val urlImage = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
        detailMovie?.let {
            with(binding) {
                tvItemTitleMovieDetail.text = detailMovie.title
                tvItemVoteMovieDetail.text = detailMovie.vote.toString()
                tvItemReleaseMovieDetail.text = detailMovie.release
                tvOverviewMovieDetail.text = detailMovie.overview
                Log.d("CHECK", detailMovie.title)

            }

            Glide.with(this)
                .load(urlImage + detailMovie.poster)
                .apply(RequestOptions().override(110, 150))
                .into(binding.imgPosterMovieDetail)


            var statusFavorite = detailMovie.favorite
            setFavorite(statusFavorite)
            binding.btnFavoriteMovie.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setMovieFavorite(detailMovie,statusFavorite)
                setFavorite(statusFavorite)
            }
        }

    }

    private fun setFavorite(state: Boolean){
        if (state){
            binding.btnFavoriteMovie.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.btnFavoriteMovie.setImageResource(R.drawable.ic_favorite_select)
        }
    }
}