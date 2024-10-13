package com.demo.demotask.data.repository

import com.demo.demotask.data.model.BaseModelMovie
import com.demo.demotask.data.model.MovieItem
import com.demo.demotask.domin.repository.Repository
import com.demo.taskdemo.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.sql.DataSource

class RepositoryImp @Inject constructor(
private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): Repository {
    override suspend fun moviesList(): Flow<Resource<List<MovieItem>>> = flow {
        emit(Resource.Loading)
        try {
            val result = remoteDataSource.nowPlayingMovies()
            localDataSource.addListOfMovies(result.results)
            val localMovies=localDataSource.getAllMovie()
            emit(Resource.Success(localMovies))

        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    override suspend fun getMovieById(id:String): Flow<Resource<MovieItem>> = flow {
        emit(Resource.Loading)
        try {
            var movie=localDataSource.getMovieById(id.toLong())
            emit(Resource.Success(movie))

        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

}