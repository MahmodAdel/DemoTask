package com.demo.demotask.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.demo.demotask.data.model.UserModel
import com.demo.demotask.presentation.vm.MainViewModel

@Composable
fun MainScreen(navHostController: NavHostController,userModel: UserModel?) {

    MainForm(navController = navHostController, userModel = userModel!!)

}