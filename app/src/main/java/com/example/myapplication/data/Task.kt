package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey
    val id: Int,
    val title: String,
    val priority: String,
    val progress: Int,
    val deadline: Date,
    val estimateTimeMinutes: Int
)