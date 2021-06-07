package com.dicoding.submissionmade.core.domain.usecase

import com.dicoding.submissionmade.core.data.Resource
import com.dicoding.submissionmade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}