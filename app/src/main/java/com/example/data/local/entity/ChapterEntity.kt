package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "chapters",
    indices = [Index(value = ["bookId", "chapterIndex"], unique = true)]
)
data class ChapterEntity(
    @PrimaryKey
    val id: String,
    val bookId: String,
    val chapterIndex: Int,
    val title: String,
    val titleNative: String,
    val subtitle: String,
    val content: String,
    val estimatedMinutes: Int = 5
)
