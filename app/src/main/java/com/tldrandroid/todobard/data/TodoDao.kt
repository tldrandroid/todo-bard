package com.tldrandroid.todobard.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert
    fun insert(task: Todo)

    @Query("SELECT * FROM todo")
    fun getAllTasks(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE completed = 1")
    fun getCompletedTasks(): List<Todo>

    @Query("UPDATE todo SET completed = 1 WHERE id = :id")
    fun completeTask(id: Int)

    @Query("DELETE FROM todo WHERE id = :id")
    fun deleteTask(id: Int)
}
