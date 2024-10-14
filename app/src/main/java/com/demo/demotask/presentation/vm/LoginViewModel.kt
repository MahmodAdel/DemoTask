package com.demo.demotask.presentation.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demotask.data.model.UserModel
import com.demo.taskdemo.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    val _login: MutableStateFlow<Resource<UserModel>> = MutableStateFlow(Resource.InitScreen())
    val login: StateFlow<Resource<UserModel>> = _login
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()
    fun login(userModel: UserModel){
        viewModelScope.launch {
            _login.value=Resource.Loading
            _isLoading.value = true
            delay(2000)
            _isLoading.value = false
            _login.value=Resource.Success(userModel)
        }
    }
}