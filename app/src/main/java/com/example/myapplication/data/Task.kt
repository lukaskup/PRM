package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Task(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)