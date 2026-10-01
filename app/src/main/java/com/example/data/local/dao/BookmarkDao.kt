package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks WHERE bookId = :bookId ORDER BY timestamp DESC")
    fun getBookmarksForBook(bookId: String): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity): Long

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmarkById(id: Long)

    @Query("DELETE FROM bookmarks WHERE bookId = :bookId AND chapterIndex = :chapterIndex")
    suspend fun removeBookmarkForChapter(bookId: String, chapterIndex: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE bookId = :bookId AND chapterIndex = :chapterIndex)")
    fun isChapterBookmarked(bookId: String, chapterIndex: Int): Flow<Boolean>
}
