package com.demo.demotask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.demotask.data.model.UserModel
import com.demo.demotask.presentation.screens.details.DetailsScreen
import com.demo.demotask.presentation.screens.login.LoginScreen
import com.demo.demotask.presentation.screens.main.MainScreen
import com.google.gson.Gson

@Composable
fun AppNavigationGraph(){
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LOGIN_SCREEN){
        composable(Routes.LOGIN_SCREEN){
            LoginScreen(navHostController = navController)
        }
        composable(Routes.MAIN_SCREEN){
            var args=it.arguments?.getString("user")
            val user = Gson().fromJson(args, UserModel::class.java)
            MainScreen(navHostController = navController,user)
        }
        composable(Routes.DETAILS_SCREEN){
            var args=it.arguments?.getString("itemId")
            DetailsScreen(navHostController = navController,args)
        }
    }

}