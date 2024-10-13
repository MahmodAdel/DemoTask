package com.demo.demotask.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen(navHostController: NavHostController,itemId:String?) {

    DetailsForm(navHostController,itemId!!)
}