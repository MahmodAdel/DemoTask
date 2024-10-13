package com.demo.demotask.data.repository

import com.demo.demotask.data.model.MovieItem


interface LocalDataSource {

    suspend fun addListOfMovies(list: List<MovieItem>) :LongArray
    suspend fun getMovieById(id:Long) :MovieItem
    suspend fun getAllMovie() :List<MovieItem>


}