package com.demo.demotask.data.dataSource.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.demotask.data.model.MovieItem

@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListOfMovies(post : List<MovieItem>) : LongArray


    @Query("SELECT * FROM movies WHERE dbId = :id")
    suspend fun getMoviesById(id:Long) : List<MovieItem>

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies() : List<MovieItem>
}