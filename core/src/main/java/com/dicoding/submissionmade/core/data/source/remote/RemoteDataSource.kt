package com.dicoding.submissionmade.core.data.source.remote

import android.util.Base64
import android.util.Log
import com.dicoding.submissionmade.core.data.source.remote.network.ApiResponse
import com.dicoding.submissionmade.core.data.source.remote.network.ApiService
import com.dicoding.submissionmade.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService){

    companion object {
        init {
            System.loadLibrary("keys")
        }
    }

    external fun getNativeKey1(): String?

    private var key1: String = String(Base64.decode(getNativeKey1(), Base64.DEFAULT))

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getTopRatedMovie(key1)
                val data = response.results
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}