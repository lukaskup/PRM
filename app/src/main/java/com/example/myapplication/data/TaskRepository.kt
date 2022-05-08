package com.example.myapplication.data

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData();

    suspend fun addTask(task: Task){
        taskDao.addTask(task);
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}