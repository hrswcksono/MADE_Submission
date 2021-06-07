package com.dicoding.submissionmade.detail

import androidx.lifecycle.ViewModel
import com.dicoding.submissionmade.core.domain.model.Movie
import com.dicoding.submissionmade.core.domain.usecase.MovieUseCase

class DetailMovieViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}