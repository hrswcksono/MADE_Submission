package com.dicoding.submissionmade.core.data.source.remote.network

import com.dicoding.submissionmade.core.data.source.remote.response.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") api_key: String
    ): ListResponse

}