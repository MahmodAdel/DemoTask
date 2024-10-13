package com.demo.demotask.presentation.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.demo.demotask.data.model.UserModel
import com.demo.demotask.presentation.navigation.Routes
import com.demo.demotask.presentation.vm.LoginViewModel
import com.demo.taskdemo.common.Resource

import com.google.gson.Gson

@Composable
fun LoginScreen(navHostController: NavHostController) {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val login by loginViewModel.login.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()
        ) {

            when(login){
                is Resource.Error -> {

                }
                is Resource.InitScreen -> {

                }
                Resource.Loading -> {

                }
                is Resource.Success -> {
                    val user = (login as Resource.Success<UserModel>).data
                    val stringUser=Gson().toJson(user)
                    navHostController.navigate(
                        Routes.MAIN_SCREEN.
                    replace("{user}",stringUser)
                    ) {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
            LoginForm(loginViewModel)

        }

}