package com.demo.demotask.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.demo.demotask.data.model.UserModel
import com.demo.demotask.presentation.navigation.Routes
import com.demo.demotask.presentation.vm.MainViewModel
import com.demo.taskdemo.common.Resource
import com.google.gson.Gson

@Composable
fun MainScreen(navHostController: NavHostController,userModel: UserModel?) {

    MainForm(navController = navHostController, userModel = userModel!!)

}