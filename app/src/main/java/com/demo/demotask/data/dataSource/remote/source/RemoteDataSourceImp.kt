package com.demo.demotask.data.dataSource.remote.source

import com.demo.demotask.data.dataSource.remote.api.ApiService
import com.demo.demotask.data.model.BaseModelMovie
import com.demo.demotask.data.repository.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    var apiService: ApiService
) : RemoteDataSource {
    init {
        System.loadLibrary("app-keys")
    }
    external fun getAPIKey(): String

    override suspend fun nowPlayingMovies(): BaseModelMovie {
       return apiService.nowPlayingMovies(api_key = getAPIKey())
    }
}