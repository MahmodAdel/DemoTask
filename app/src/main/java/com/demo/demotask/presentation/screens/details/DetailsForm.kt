package com.demo.demotask.presentation.screens.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.demo.demotask.presentation.component.TopBar
import com.demo.demotask.presentation.ui.theme.grayLight700
import com.demo.demotask.presentation.vm.DetailsViewModel
import com.demo.taskdemo.common.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsForm(navHostController: NavHostController, itemId: String) {

    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val movie by detailsViewModel.movie.collectAsState()

    LaunchedEffect(Unit) {
        detailsViewModel.getMoviesById(itemId)
    }
    Scaffold(
        topBar = {
            TopBar(stringResource(id = R.string.item), navHostController)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 60.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = grayLight700),
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    ),
                ) {
                    if (movie is Resource.Success) {
                        val movie = (movie as Resource.Success<MovieItem>).data

                        Row(
                            modifier = Modifier.padding(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "title:",
                                textAlign = TextAlign.Start,
                                fontSize = 14.sp,
                            )
                            Spacer(
                                Modifier
                                    .weight(1f)
                            )
                            Text(
                                text = "${movie.title}",
                                color = Color.Black,
                                style = TextStyle(
                                    textDirection = TextDirection.ContentOrLtr,
                                    fontWeight = FontWeight.Bold
                                ),
                                fontSize = 14.sp
                            )
                        }
                        Row(
                            modifier = Modifier.padding(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Overview:",
                                textAlign = TextAlign.Start,
                                fontSize = 14.sp,
                            )
                            Spacer(
                                Modifier
                                    .weight(1f)
                            )
                            Text(
                                text = "${movie.overview}",
                                color = Color.Black,
                                style = TextStyle(
                                    textDirection = TextDirection.ContentOrLtr,
                                    fontWeight = FontWeight.Bold
                                ),
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        })


}