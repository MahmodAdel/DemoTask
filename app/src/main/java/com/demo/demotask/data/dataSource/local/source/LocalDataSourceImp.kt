package com.demo.demotask.data.dataSource.local.source

import com.demo.demotask.data.dataSource.local.db.ServiceDao
import com.demo.demotask.data.model.MovieItem
import com.demo.demotask.data.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(val serviceDao: ServiceDao) : LocalDataSource {
    override suspend fun addListOfMovies(list: List<MovieItem>): LongArray {
       return serviceDao.addListOfMovies(list)
    }

    override suspend fun getMovieById(id: Long): MovieItem {
        return serviceDao.getMoviesById(id)[0]
    }

    override suspend fun getAllMovie(): List<MovieItem> {
        return serviceDao.getAllMovies()
    }

}