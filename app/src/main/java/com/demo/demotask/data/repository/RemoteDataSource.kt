package com.demo.demotask.data.repository

import com.demo.demotask.data.model.BaseModelMovie


interface RemoteDataSource {

    suspend fun nowPlayingMovies(

    ): BaseModelMovie
}