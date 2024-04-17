package com.example.todolist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todolist.R
import com.example.todolist.items.TaskItem
import com.example.todolist.items.TaskViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier,
) {
    Column {
        if (taskViewModel.tasks.value.isEmpty()) {
            Text(
                text = stringResource(R.string.not_forgot),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                fontSize = 30.sp,
                lineHeight = 50.sp,
                textAlign = TextAlign.Center,
            )
            Column {
                Image(
                    painter = painterResource(id = R.drawable.home_screen),
                    contentDescription = "Home Screen",
                    contentScale = ContentScale.Crop,
                    modifier = modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(R.string.for_now_you_have_nothing_to_do_have_a_nice_rest),
                    modifier = modifier
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    lineHeight = 35.sp,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            Text(
                text = stringResource(R.string.not_forgot),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                fontSize = 30.sp,
                lineHeight = 50.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(R.string.tasks),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )
            taskViewModel.tasks.value.forEach { task ->
                TaskItem(task) { taskId ->
                    navHostController.navigate("S_3/$taskId")
                }
            }
        }
        FloatingActionButton(
            onClick = { navHostController.navigate("S_2") },
            modifier = modifier
                .padding(20.dp)
                .offset(y = 25.dp)
                .align(Alignment.End),
            containerColor = Color.Black,
            contentColor = Color.White // Set content color to white
        ) {
            Text(
                modifier = modifier.padding(10.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Light,
                text = "+",
            )
        }
    }
}