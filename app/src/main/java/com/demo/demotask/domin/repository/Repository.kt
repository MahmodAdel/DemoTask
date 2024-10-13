package com.demo.demotask.domin.repository

import com.demo.demotask.data.model.BaseModelMovie
import com.demo.demotask.data.model.MovieItem
import com.demo.taskdemo.common.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun moviesList():Flow<Resource<List<MovieItem>>>
    suspend fun getMovieById(id:String):Flow<Resource<MovieItem>>



}