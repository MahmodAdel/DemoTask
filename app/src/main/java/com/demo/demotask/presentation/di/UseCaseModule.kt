package com.demo.demotask.presentation.di

import com.demo.demotask.domin.useCase.MoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.demo.demotask.domin.repository.Repository
import com.demo.demotask.domin.useCase.GetMovieByIdUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetProductUSeCase(mainRepository: Repository):MoviesUseCase{
        return MoviesUseCase(mainRepository)
    }

    @Singleton
    @Provides
    fun provideGetMovieByIDUSeCase(mainRepository: Repository):GetMovieByIdUseCase{
        return GetMovieByIdUseCase(mainRepository)
    }
}