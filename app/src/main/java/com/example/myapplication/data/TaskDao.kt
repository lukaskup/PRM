package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

// Dao - Data Access Object
@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Task>>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}