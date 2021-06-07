package com.dicoding.submissionmade.core.data.source.local

import com.dicoding.submissionmade.core.data.source.local.entity.MovieEntity
import com.dicoding.submissionmade.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao){

    fun getAllMovie(): Flow<List<MovieEntity>> = mMovieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = mMovieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = mMovieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieDao.updateFavouriteMovie(movie)
    }

}