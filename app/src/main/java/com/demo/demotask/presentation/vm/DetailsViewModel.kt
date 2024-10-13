package com.demo.demotask.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demotask.data.model.BaseModelMovie
import com.demo.demotask.data.model.MovieItem
import com.demo.demotask.domin.useCase.GetMovieByIdUseCase
import com.demo.demotask.domin.useCase.MoviesUseCase
import com.demo.taskdemo.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(val movieUSeCase: GetMovieByIdUseCase): ViewModel() {
    val _movie: MutableStateFlow<Resource<MovieItem>> = MutableStateFlow(
        Resource.InitScreen())
    val movie: StateFlow<Resource<MovieItem>> = _movie

    fun getMoviesById(id:String){
        viewModelScope.launch{
            movieUSeCase.execute(id).collect{
                when(it){
                    is Resource.Error -> {

                    }
                    is Resource.InitScreen -> {

                    }
                    Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        _movie.value=it
                    }
                }
            }
        }
    }
}