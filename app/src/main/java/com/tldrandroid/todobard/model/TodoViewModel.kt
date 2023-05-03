package com.tldrandroid.todobard.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tldrandroid.todobard.data.Todo
import com.tldrandroid.todobard.data.TodoDatabase
import kotlinx.coroutines.launch

class TodoViewModel(
    context: Context
) : ViewModel() {
    private val todoDao = TodoDatabase.getInstance(context).getTodoDao()
    val tasks = todoDao.getAllTasks()

    fun addTask(task: Todo) {
        viewModelScope.launch {
            todoDao.insert(task)
        }
    }

    fun completeTask(id: Int) {
        viewModelScope.launch {
            todoDao.completeTask(id)
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            todoDao.deleteTask(id)
        }
    }
}
