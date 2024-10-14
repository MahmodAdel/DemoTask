package com.demo.demotask.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demotask.data.model.BaseModelMovie
import com.demo.demotask.data.model.MovieItem
import com.demo.demotask.domin.useCase.MoviesUseCase
import com.demo.taskdemo.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val moviesUseCase: MoviesUseCase): ViewModel() {
    val _movies: MutableStateFlow<Resource<List<MovieItem>>> = MutableStateFlow(
        Resource.InitScreen())
    val movies: StateFlow<Resource<List<MovieItem>>> = _movies
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()
    fun getMovies(){
        viewModelScope.launch{
            moviesUseCase.execute(null).collect{
                when(it){
                    is Resource.Error -> {
                        _isLoading.value = false

                    }
                    is Resource.InitScreen -> {
                        _isLoading.value = false

                    }
                    Resource.Loading -> {
                        _isLoading.value = true

                    }
                    is Resource.Success -> {
                        _isLoading.value = false

                        _movies.value=it
                    }
                }
            }
        }
    }
}