package com.demo.demotask.domin.useCase

import com.demo.demotask.data.model.BaseModelMovie
import com.demo.demotask.data.model.MovieItem
import com.demo.demotask.domin.repository.Repository
import com.demo.demotask.domin.useCase.base.BaseUseCase
import com.demo.taskdemo.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<List<MovieItem>, Nothing>() {
    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<MovieItem>>> {
        return repository.moviesList().flowOn(Dispatchers.IO)
    }
}