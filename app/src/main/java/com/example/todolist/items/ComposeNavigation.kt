package com.example.todolist.items

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.screens.EditeTaskScreen
import com.example.todolist.screens.HomeScreen
import com.example.todolist.screens.TaskScreen

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun ComposeNavigation(taskViewModel: TaskViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "S_1") {
        composable("S_1") {
            HomeScreen(navController, taskViewModel)
        }
        composable("S_2") {
            TaskScreen(navController, taskViewModel)
        }
        composable("S_3/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            EditeTaskScreen(navController, taskViewModel, taskId)
        }
        composable("S_4/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            val task = taskViewModel.getTaskById(taskId)
            if (task != null) {
                TaskItem(task) {}
            } else {
                Text("Task not found")
            }
        }
    }
}