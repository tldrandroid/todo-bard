package com.tldrandroid.todobard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tldrandroid.todobard.model.TodoViewModel

@ExperimentalMaterial3Api
@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    val tasks by viewModel.tasks.observeAsState(emptyList())
    var showAddTaskDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Todo List")
                }
            )
        },
        floatingActionButton = {
            if (showAddTaskDialog) {
                // Create a new Dialog object
                AlertDialog(
                    title = { Text("Add Task") },
                    text = {
                        // Create a TextField composable
                        TextField(
                            value = "",
                            onValueChange = {
                                // Set the value of the TextField
                                dialog.content.text = it
                            }
                        )
                    },
                    confirmButton = {
                        // Add the task to the list
                        viewModel.addTask(dialog.content.text)
                    }
                )
            }

            FloatingActionButton(
                onClick = {
                    showAddTaskDialog = true
                }
            ) {
                Text("Add Task")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier.padding(8.dp)
            ) {
                items(tasks.size) { index ->
                    Checkbox(
                        checked = tasks[index].completed,
                        onCheckedChange = { viewModel.completeTask(tasks[index].id) }
                    )
                    Text(tasks[index].title)
                    // Add a long-press listener to the task
                    onLongClick = {
                        // Create a new Dialog object
                        val dialog = Dialog(
                            title = "Delete Task",
                            content = {
                                // Create a TextField composable
                                TextField(
                                    value = "",
                                    onValueChange = {
                                        // Set the value of the TextField
                                        dialog.content.text = it
                                    }
                                )
                            },
                            onDismissRequest = {
                                // Remove the dialog from the screen
                                dialog.dismiss()
                            },
                            onPositiveButtonClick = {
                                // Delete the task from the list
                                viewModel.deleteTask(task)
                                dialog.dismiss()
                            }
                        )

                        // Show the dialog
                        dialog.show()
                    }
                }
            }
        }
    }
}
