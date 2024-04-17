package com.example.todolist.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.example.todolist.ui.theme.*

@Composable
fun TaskItem(task: Task, onItemClick: (Int) -> Unit) {
    val checkedState = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(task.id) }
            .padding(10.dp),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.cardColors(
            when (task.priority) {
                TaskPriority.REMEMBER -> Color.Black
                TaskPriority.IMPORTANT -> taskRed
                TaskPriority.NORMAL -> taskGreen
                TaskPriority.NOT_IMPORTANT -> taskYellow
                TaskPriority.SOME_DAY -> taskBlue
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 25.dp),
        border = null
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(bottom = 7.dp),
                    text = task.title,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = task.description,
                    color = Color.White,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Checkbox(
                modifier = Modifier.padding(10.dp),
                colors = CheckboxDefaults.colors(Color.White),
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
        }
    }
}
