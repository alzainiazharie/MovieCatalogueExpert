package com.azharie.alzaini.moviecatalogueexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azharie.alzaini.moviecatalogueexpert.core.databinding.ItemsMovieFavoriteBinding
import com.azharie.alzaini.moviecatalogueexpert.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapterFavorite : RecyclerView.Adapter<MovieAdapterFavorite.ViewHolder>(){
    private var listMovies = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null
    fun setMoviesFavorite(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    inner class ViewHolder(private val binding: ItemsMovieFavoriteBinding) : RecyclerView.ViewHolder(binding.root){
        private val urlImage = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
        fun bind(movies: Movie) {
            with(binding) {
                tvItemTitleMovieFavorite.text = movies.title

                Glide.with(itemView.context)
                    .load(urlImage + movies.poster)
                    .apply(RequestOptions().override(100, 150))
                    .into(imgPosterMovieFavorite)

                tvTotalVote.text = movies.vote.toString()
                tvItemReleaseMovieFavorite.text = movies.release

                itemView.setOnClickListener {
                    onItemClick?.invoke(listMovies[adapterPosition])
                }

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val itemsMovieBindingFavorite = ItemsMovieFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsMovieBindingFavorite)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

}