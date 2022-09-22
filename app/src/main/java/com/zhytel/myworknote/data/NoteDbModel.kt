package com.zhytel.myworknote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_items")
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val day: String
)