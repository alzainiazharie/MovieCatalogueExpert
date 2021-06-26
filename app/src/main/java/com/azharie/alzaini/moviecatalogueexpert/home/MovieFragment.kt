package com.azharie.alzaini.moviecatalogueexpert.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.azharie.alzaini.moviecatalogueexpert.core.data.Resource
import com.azharie.alzaini.moviecatalogueexpert.databinding.FragmentMovieBinding
import com.azharie.alzaini.moviecatalogueexpert.detail.DetailMovieActivity
import com.azharie.alzaini.moviecatalogueexpert.core.ui.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val movieViewModel : MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {


            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            movieViewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            loadingMovie(true)
                            Log.d("MOVIE","Loading ViewModel")
                        }
                        is Resource.Success -> {
                            loadingMovie(false)
                            movieAdapter.setMovies(movies.data)
                            movieAdapter.notifyDataSetChanged()
                            Log.d("MOVIE","Success ViewModel")
                        }
                        is Resource.Error -> {
                            loadingMovie(false)
                            Log.d("MOVIE","Fail ViewModel")
                        }
                    }
                }

            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun loadingMovie(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}