package com.example.kotlinrecyclerview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "questionId")
    val question_id: String,

    val title: String,

    val link: String
)