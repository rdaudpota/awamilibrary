package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val titleNative: String,
    val author: String,
    val authorNative: String,
    val category: String,
    val language: String,
    val description: String,
    val publicationYear: String,
    val pagesCount: Int,
    val totalChapters: Int,
    val isDownloaded: Boolean = true,
    val isFavorite: Boolean = false,
    val currentChapterIndex: Int = 0,
    val readingProgressPercent: Float = 0f,
    val lastReadTimestamp: Long = 0L,
    val coverColorHex: Long = 0xFF144534L,
    val coverIconType: String = "CLASSIC"
)
