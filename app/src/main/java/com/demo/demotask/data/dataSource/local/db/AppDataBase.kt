package com.demo.demotask.data.dataSource.local.db
import com.demo.demotask.data.model.MovieItem
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieItem::class], version = 1)
abstract class AppDataBase :RoomDatabase(){

    abstract fun serviceDao(): ServiceDao


}