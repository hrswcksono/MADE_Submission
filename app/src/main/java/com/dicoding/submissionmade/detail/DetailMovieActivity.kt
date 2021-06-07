package com.dicoding.submissionmade.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.dicoding.submissionmade.R
import com.dicoding.submissionmade.core.domain.model.Movie
import com.dicoding.submissionmade.databinding.ActivityDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val args : DetailMovieActivityArgs by navArgs()

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val detailMovie = args.movie
        showDetailTourism(detailMovie)
    }

    private fun showDetailTourism(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            binding.content.tvOview.text = detailMovie.overview
            binding.content.tvReleaseDate.text = detailMovie.releaseDate
            binding.content.tvPopularity.text = detailMovie.popularity.toString()
            binding.content.tvVoteAverage.text = detailMovie.voteAverage.toString()
            Glide.with(this@DetailMovieActivity)
                .load("https://image.tmdb.org/t/p/w500${detailMovie.backdropPath}")
                .into(binding.ivDetailImage)
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${detailMovie.posterPath}")
                .into(binding.content.imgAvatar)
            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}