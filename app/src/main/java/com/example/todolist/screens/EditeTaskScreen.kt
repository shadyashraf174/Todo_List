package com.example.todolist.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todolist.R
import com.example.todolist.items.TaskViewModel
import com.example.todolist.ui.theme.taskGreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditeTaskScreen(
    navHostController: NavHostController,
    taskViewModel: TaskViewModel,
    taskId: Int
) {
    val task = taskViewModel.getTaskById(taskId = 1)

    task?.let {

        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.padding(8.dp),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.Black,
                    ),
                    title = {
                        Text(
                            text = stringResource(R.string.task),
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 25.sp,
                            lineHeight = 50.sp,
                            //textAlign = TextAlign.Center,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navHostController.navigate("S_1") }) {
                            Icon(

                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(it)

            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text(
                        text = task.title,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Column {
                        IconButton(onClick = {
                            navHostController.navigate("S_2")
                        }) {
                            Icon(
                                modifier = Modifier,
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                tint = Color.Blue
                            )
                        }
                        Text(
                            modifier = Modifier.padding(end = 30.dp),
                            text = stringResource(R.string.completed),
                            color = taskGreen

                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = task.description,
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                ) {

                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                    )
                    Text(
                        text = "Before ${task.deadline}",
                        modifier = Modifier.padding(end = 50.dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.in_edit_screen),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 30.dp),
                    )
                }
            }
        }
    }
}

