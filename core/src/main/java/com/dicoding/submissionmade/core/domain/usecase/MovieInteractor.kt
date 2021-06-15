package com.dicoding.submissionmade.core.domain.usecase

import com.dicoding.submissionmade.core.domain.model.Movie
import com.dicoding.submissionmade.core.domain.repository.IMovieRepository

class MovieInteractor (private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()
    override fun getFavoriteMovie()= movieRepository.getFavoriteMovie()
    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}