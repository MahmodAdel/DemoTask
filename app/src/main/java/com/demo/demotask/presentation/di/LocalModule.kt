package com.demo.demotask.presentation.di

import android.content.Context
import androidx.room.Room
import com.demo.demotask.data.dataSource.local.db.AppDataBase
import com.demo.demotask.data.dataSource.local.db.ServiceDao
import com.demo.demotask.data.dataSource.local.source.LocalDataSourceImp
import com.demo.demotask.data.repository.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "database"
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: AppDataBase) = db.serviceDao() // The reason we can implement a Dao for the database

    @Singleton
    @Provides
    fun provideLocalRepository(
        serviceDao: ServiceDao
    ): LocalDataSource {
        return LocalDataSourceImp(
            serviceDao
        )
    }
}