package com.demo.demotask.data.dataSource.remote.api

import com.demo.demotask.data.model.BaseModelMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun nowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") api_key: String
    ): BaseModelMovie
}