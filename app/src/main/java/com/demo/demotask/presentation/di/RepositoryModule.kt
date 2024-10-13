package com.demo.demotask.presentation.di

import android.widget.RemoteViews.RemoteResponse
import com.demo.demotask.data.dataSource.local.db.ServiceDao
import com.demo.demotask.data.dataSource.local.source.LocalDataSourceImp
import com.demo.demotask.data.dataSource.remote.api.ApiService
import com.demo.demotask.data.dataSource.remote.source.RemoteDataSourceImp
import com.demo.demotask.data.repository.LocalDataSource
import com.demo.demotask.data.repository.RemoteDataSource
import com.demo.demotask.data.repository.RepositoryImp
import com.demo.demotask.domin.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {



    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImp(
            localDataSource,remoteDataSource
        )
    }


}