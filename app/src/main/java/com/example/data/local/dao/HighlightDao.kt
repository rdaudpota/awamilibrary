package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.HighlightEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HighlightDao {
    @Query("SELECT * FROM highlights WHERE bookId = :bookId ORDER BY timestamp DESC")
    fun getHighlightsForBook(bookId: String): Flow<List<HighlightEntity>>

    @Query("SELECT * FROM highlights ORDER BY timestamp DESC")
    fun getAllHighlights(): Flow<List<HighlightEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHighlight(highlight: HighlightEntity): Long

    @Query("DELETE FROM highlights WHERE id = :id")
    suspend fun deleteHighlightById(id: Long)
}
