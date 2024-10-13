package com.demo.demotask.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.demo.demotask.R
import com.demo.demotask.data.model.BaseModelMovie
import com.demo.demotask.data.model.MovieItem
import com.demo.demotask.data.model.UserModel
import com.demo.demotask.presentation.component.TopBar
import com.demo.demotask.presentation.navigation.Routes
import com.demo.demotask.presentation.ui.theme.white
import com.demo.demotask.presentation.vm.MainViewModel
import com.demo.taskdemo.common.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainForm(
    navController: NavHostController,
    userModel: UserModel,
) {

    val mainViewModel = hiltViewModel<MainViewModel>()
    val listMovies by mainViewModel.movies.collectAsState()
    LaunchedEffect(Unit) {
        mainViewModel.getMovies()
    }

    Scaffold(
        topBar = {
            TopBar(stringResource(id = R.string.mainList), navController)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 60.dp)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = white),
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    ),
                ) {
                    Row(
                        modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                            Text(
                                text = userModel.userName,
                                textAlign = TextAlign.Start,
                                fontSize = 13.sp,
                                modifier = Modifier.weight(1f)
                            )

                        Text(
                            text = "${userModel.loginTime}",
                            color = Color.Black,
                            style = TextStyle(
                                textDirection = TextDirection.ContentOrLtr,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.movies_list),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (listMovies is Resource.Success) {
                        val movies= (listMovies as Resource.Success<List<MovieItem>>).data

                        items(movies) { item ->
                            ServiceCard(item) {
                                starService(navController,it )
                            }
                            Divider(color = Color.Gray, thickness = 1.dp)
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }
        })
}
fun starService(
    navController: NavHostController,
    it: MovieItem,
) {
    navController.navigate(
        Routes.DETAILS_SCREEN.
        replace("{itemId}",it.dbId.toString())
    ) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }

        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun ServiceCard(item: MovieItem, onClick: (MovieItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        )
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .background(Color.Transparent)
                .clickable { onClick(item) }
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.title),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                    Spacer(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color.Red)
                    ) // height and background only for demonstration
                    Text(
                        text = "${item.originalTitle}",
                        color = Color.Black,
                        style = TextStyle(
                            textDirection = TextDirection.ContentOrLtr,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = stringResource(id = R.string.back_drop),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                    Spacer(
                        Modifier
                            .weight(3f)
                            .fillMaxHeight()
                            .background(Color.White)
                    ) // height and background only for demonstration
                    Text(
                        text = "${item.backdropPath}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = stringResource(id = R.string.language),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                    Spacer(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color.White)
                    ) // height and background only for demonstration
                    Text(
                        text = "${item.originalLanguage}",
                        color = Color.Black,
                        style = TextStyle(
                            textDirection = TextDirection.ContentOrLtr,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                }
            }

        }
    }
}
