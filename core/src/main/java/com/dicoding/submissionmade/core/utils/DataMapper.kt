package com.dicoding.submissionmade.core.utils

import com.dicoding.submissionmade.core.data.source.local.entity.MovieEntity
import com.dicoding.submissionmade.core.data.source.remote.response.MovieResponse
import com.dicoding.submissionmade.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                overview = it.overview,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                overview = it.overview,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(it: Movie) = MovieEntity(
        movieId = it.movieId,
        overview = it.overview,
        backdropPath = it.backdropPath,
        popularity = it.popularity,
        posterPath = it.posterPath,
        releaseDate = it.releaseDate,
        title = it.title,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount,
        isFavorite = it.isFavorite
    )
}