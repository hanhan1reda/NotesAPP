package com.example.noteapp2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title :String,
    val description :String,
    val isFavorite :Boolean
)
