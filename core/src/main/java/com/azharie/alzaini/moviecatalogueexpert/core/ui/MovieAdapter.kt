package com.azharie.alzaini.moviecatalogueexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azharie.alzaini.moviecatalogueexpert.core.databinding.ItemsMovieBinding
import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val urlImage = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"

        fun bind(movies: Movie) {
            with(binding) {
                tvItemTitleMovie.text = movies.title

                Glide.with(itemView.context)
                    .load(urlImage + movies.poster)
                    .apply(RequestOptions().override(100, 150))
                    .into(imgPosterMovie)

                tvTotalVote.text = movies.vote.toString()
                tvItemReleaseMovie.text = movies.release

                itemView.setOnClickListener {
                    onItemClick?.invoke(listMovies[adapterPosition])
                }

            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

}