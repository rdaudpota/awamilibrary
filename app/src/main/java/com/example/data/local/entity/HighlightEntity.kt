package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highlights")
data class HighlightEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bookId: String,
    val chapterIndex: Int,
    val selectedText: String,
    val note: String = "",
    val colorTag: String = "GOLD",
    val timestamp: Long = System.currentTimeMillis()
)
