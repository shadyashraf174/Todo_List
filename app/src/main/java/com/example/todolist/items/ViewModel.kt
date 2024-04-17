package com.example.todolist.items

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class Task(
    val id: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val priority: TaskPriority,
    val deadline: String,
    val category: String,
    val completed: Boolean = false
)
enum class TaskPriority {
    REMEMBER,
    IMPORTANT,
    NORMAL,
    NOT_IMPORTANT,
    SOME_DAY
}
class TaskViewModel : ViewModel() {
    val tasks: MutableState<List<Task>> = mutableStateOf(emptyList())

    fun addTask(task: Task) {
        tasks.value += task
    }
    fun getTaskById(taskId: Int): Task? {
        return tasks.value.find { it.id == taskId }
    }
}

