@file:Suppress("NAME_SHADOWING")

package com.example.todolist.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todolist.R
import com.example.todolist.items.Task
import com.example.todolist.items.TaskPriority
import com.example.todolist.items.TaskViewModel
import com.example.todolist.ui.theme.taskBlue
import com.example.todolist.ui.theme.taskGreen
import com.example.todolist.ui.theme.taskRed
import com.example.todolist.ui.theme.taskYellow
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox(onPrioritySelected: (Color) -> Unit) {
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("REMEMBER", "IMPORTANT", "NORMAL", "NOT_IMPORTANT", "SOME_DAY")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 35.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            onPrioritySelected(getColorForPriority(item))
                        }
                    )
                }
            }
        }
    }
}

fun getColorForPriority(priority: String): Color {
    return when (priority) {
        "REMEMBER" -> Color.Black
        "IMPORTANT" -> taskRed
        "NORMAL" -> taskGreen
        "NOT_IMPORTANT" -> taskYellow
        "SOME_DAY" -> taskBlue
        else -> Color.Black
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    navHostController: NavHostController,
    taskViewModel: TaskViewModel,
) {
    val chartLimit = 120
    val message = remember { mutableStateOf("") }
    val messageTwo = remember { mutableStateOf("") }
    val mDate = remember { mutableStateOf("") }
    var cardColor by remember { mutableStateOf(Color.Black) }

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
                        text = stringResource(R.string.add_task),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 25.sp,
                        lineHeight = 50.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(R.string.title),
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )
            TextField(
                value = message.value,
                onValueChange = { newText -> message.value = newText },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
            OutlinedTextField(
                value = messageTwo.value,
                onValueChange = { newText -> messageTwo.value = newText.take(chartLimit) },
                label = { Text(stringResource(R.string.description)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                supportingText = {
                    Row {
                        Text("")
                        Spacer(Modifier.weight(1f))
                        Text("${messageTwo.value.length}/$chartLimit")
                    }
                },
            )
            Box(modifier = Modifier.padding(start = 16.dp, end = 0.dp)) {
                val mContext = LocalContext.current
                val mYear: Int
                val mMonth: Int
                val mDay: Int
                val mCalendar = Calendar.getInstance()
                mYear = mCalendar.get(Calendar.YEAR)
                mMonth = mCalendar.get(Calendar.MONTH)
                mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
                mCalendar.time = Date()
                val mDate = remember { mutableStateOf("") }
                val mDatePickerDialog = DatePickerDialog(
                    mContext,
                    { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                        mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
                    }, mYear, mMonth, mDay
                )
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = mDate.value,
                        onValueChange = {},
                        label = { Text(stringResource(R.string.deadline)) },
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    IconButton(onClick = {
                        mDatePickerDialog.show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = null,
                            tint = Color.Blue
                        )
                    }
                }
            }
            Demo_ExposedDropdownMenuBox { color ->
                cardColor = color
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    val task = Task(
                        id = taskViewModel.tasks.value.size + 1,
                        title = message.value,
                        subtitle = messageTwo.value,
                        description = messageTwo.value,
                        deadline = mDate.value,
                        category = "",
                        priority = when (cardColor) {
                            Color.Black -> TaskPriority.REMEMBER
                            taskRed -> TaskPriority.IMPORTANT
                            taskGreen -> TaskPriority.NORMAL
                            taskYellow -> TaskPriority.NOT_IMPORTANT
                            taskBlue -> TaskPriority.SOME_DAY
                            else -> TaskPriority.REMEMBER
                        },
                    )
                    taskViewModel.addTask(task)
                    navHostController.popBackStack()
                },
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.End),
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(cardColor)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}