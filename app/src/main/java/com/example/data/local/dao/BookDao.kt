package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: String): Flow<BookEntity?>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookByIdOnce(id: String): BookEntity?

    @Query("SELECT * FROM books WHERE isFavorite = 1 ORDER BY title ASC")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE lastReadTimestamp > 0 ORDER BY lastReadTimestamp DESC")
    fun getRecentlyReadBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isDownloaded = 1 ORDER BY title ASC")
    fun getDownloadedBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE title LIKE '%' || :query || '%' OR titleNative LIKE '%' || :query || '%' OR author LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%'")
    fun searchBooks(query: String): Flow<List<BookEntity>>

    @Query("SELECT COUNT(*) FROM books")
    suspend fun getBooksCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Query("UPDATE books SET currentChapterIndex = :chapterIndex, readingProgressPercent = :progressPercent, lastReadTimestamp = :timestamp WHERE id = :bookId")
    suspend fun updateProgress(bookId: String, chapterIndex: Int, progressPercent: Float, timestamp: Long)

    @Query("UPDATE books SET isFavorite = :isFav WHERE id = :bookId")
    suspend fun updateFavorite(bookId: String, isFav: Boolean)

    @Query("UPDATE books SET isDownloaded = :downloaded WHERE id = :bookId")
    suspend fun updateDownloaded(bookId: String, downloaded: Boolean)

    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteBookById(id: String)
}
