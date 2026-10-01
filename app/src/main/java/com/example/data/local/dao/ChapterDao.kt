package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.ChapterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {
    @Query("SELECT * FROM chapters WHERE bookId = :bookId ORDER BY chapterIndex ASC")
    fun getChaptersForBook(bookId: String): Flow<List<ChapterEntity>>

    @Query("SELECT * FROM chapters WHERE bookId = :bookId AND chapterIndex = :index")
    fun getChapter(bookId: String, index: Int): Flow<ChapterEntity?>

    @Query("SELECT * FROM chapters WHERE bookId = :bookId AND chapterIndex = :index")
    suspend fun getChapterOnce(bookId: String, index: Int): ChapterEntity?

    @Query("SELECT COUNT(*) FROM chapters WHERE bookId = :bookId")
    suspend fun getChapterCount(bookId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapters(chapters: List<ChapterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(chapter: ChapterEntity)

    @Query("DELETE FROM chapters WHERE bookId = :bookId")
    suspend fun deleteChaptersForBook(bookId: String)
}
