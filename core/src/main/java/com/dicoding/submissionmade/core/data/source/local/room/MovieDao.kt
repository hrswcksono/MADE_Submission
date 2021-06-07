package com.dicoding.submissionmade.core.data.source.local.room

import androidx.room.*
import com.dicoding.submissionmade.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_tbl")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_tbl where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavouriteMovie(movie: MovieEntity)

}