package com.dicoding.submissionmade.core.data.source.remote

import android.util.Log
import com.dicoding.submissionmade.core.BuildConfig
import com.dicoding.submissionmade.core.data.source.remote.network.ApiResponse
import com.dicoding.submissionmade.core.data.source.remote.network.ApiService
import com.dicoding.submissionmade.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource (private val apiService: ApiService){

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getTopRatedMovie("d255610c689db824e696324f5c705e3b")
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