package com.dicoding.submissionmade.core.data.source.remote.network

import com.dicoding.submissionmade.core.BuildConfig
import com.dicoding.submissionmade.core.data.source.remote.response.ListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}")
    suspend fun getTopRatedMovie(
    ): ListResponse
}