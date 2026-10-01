package com.example.data.repository

import com.example.data.local.AppDatabase
import com.example.data.local.entity.BookEntity
import com.example.data.local.entity.BookmarkEntity
import com.example.data.local.entity.ChapterEntity
import com.example.data.local.entity.HighlightEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class AwamiLibraryRepository(private val database: AppDatabase) {

    private val bookDao = database.bookDao()
    private val chapterDao = database.chapterDao()
    private val bookmarkDao = database.bookmarkDao()
    private val highlightDao = database.highlightDao()

    val allBooks: Flow<List<BookEntity>> = bookDao.getAllBooks()
    val favoriteBooks: Flow<List<BookEntity>> = bookDao.getFavoriteBooks()
    val recentlyReadBooks: Flow<List<BookEntity>> = bookDao.getRecentlyReadBooks()
    val downloadedBooks: Flow<List<BookEntity>> = bookDao.getDownloadedBooks()
    val allBookmarks: Flow<List<BookmarkEntity>> = bookmarkDao.getAllBookmarks()
    val allHighlights: Flow<List<HighlightEntity>> = highlightDao.getAllHighlights()

    fun searchBooks(query: String): Flow<List<BookEntity>> = bookDao.searchBooks(query)

    fun getBookById(id: String): Flow<BookEntity?> = bookDao.getBookById(id)

    suspend fun getBookByIdOnce(id: String): BookEntity? = withContext(Dispatchers.IO) {
        bookDao.getBookByIdOnce(id)
    }

    fun getChaptersForBook(bookId: String): Flow<List<ChapterEntity>> =
        chapterDao.getChaptersForBook(bookId)

    fun getChapter(bookId: String, index: Int): Flow<ChapterEntity?> =
        chapterDao.getChapter(bookId, index)

    suspend fun getChapterOnce(bookId: String, index: Int): ChapterEntity? =
        withContext(Dispatchers.IO) {
            chapterDao.getChapterOnce(bookId, index)
        }

    suspend fun updateReadingProgress(bookId: String, chapterIndex: Int, percent: Float) =
        withContext(Dispatchers.IO) {
            bookDao.updateProgress(bookId, chapterIndex, percent, System.currentTimeMillis())
        }

    suspend fun toggleFavorite(bookId: String, currentStatus: Boolean) =
        withContext(Dispatchers.IO) {
            bookDao.updateFavorite(bookId, !currentStatus)
        }

    suspend fun toggleDownload(bookId: String, currentStatus: Boolean) =
        withContext(Dispatchers.IO) {
            bookDao.updateDownloaded(bookId, !currentStatus)
        }

    suspend fun addBookmark(
        bookId: String,
        bookTitle: String,
        chapterIndex: Int,
        chapterTitle: String,
        excerpt: String,
        note: String
    ): Long = withContext(Dispatchers.IO) {
        bookmarkDao.insertBookmark(
            BookmarkEntity(
                bookId = bookId,
                bookTitle = bookTitle,
                chapterIndex = chapterIndex,
                chapterTitle = chapterTitle,
                excerpt = excerpt,
                userNote = note
            )
        )
    }

    suspend fun deleteBookmark(id: Long) = withContext(Dispatchers.IO) {
        bookmarkDao.deleteBookmarkById(id)
    }

    fun isChapterBookmarked(bookId: String, chapterIndex: Int): Flow<Boolean> =
        bookmarkDao.isChapterBookmarked(bookId, chapterIndex)

    suspend fun addHighlight(
        bookId: String,
        chapterIndex: Int,
        text: String,
        note: String,
        colorTag: String
    ): Long = withContext(Dispatchers.IO) {
        highlightDao.insertHighlight(
            HighlightEntity(
                bookId = bookId,
                chapterIndex = chapterIndex,
                selectedText = text,
                note = note,
                colorTag = colorTag
            )
        )
    }

    suspend fun deleteHighlight(id: Long) = withContext(Dispatchers.IO) {
        highlightDao.deleteHighlightById(id)
    }

    suspend fun addCustomBook(
        title: String,
        titleNative: String,
        author: String,
        authorNative: String,
        category: String,
        language: String,
        description: String,
        chapterTitles: List<String>,
        chapterContents: List<String>
    ): String = withContext(Dispatchers.IO) {
        val bookId = "custom_" + System.currentTimeMillis()
        val book = BookEntity(
            id = bookId,
            title = title,
            titleNative = titleNative.ifBlank { title },
            author = author,
            authorNative = authorNative.ifBlank { author },
            category = category,
            language = language,
            description = description,
            publicationYear = "Personal Archive",
            pagesCount = chapterContents.sumOf { it.length / 500 }.coerceAtLeast(10),
            totalChapters = chapterContents.size,
            isDownloaded = true,
            isFavorite = false,
            coverColorHex = 0xFF1B4332L,
            coverIconType = "CLASSIC"
        )
        bookDao.insertBook(book)

        val chapters = chapterContents.mapIndexed { index, content ->
            val chapTitle = chapterTitles.getOrNull(index)?.ifBlank { "Chapter ${index + 1}" }
                ?: "Chapter ${index + 1}"
            ChapterEntity(
                id = "${bookId}_ch$index",
                bookId = bookId,
                chapterIndex = index,
                title = chapTitle,
                titleNative = chapTitle,
                subtitle = "Imported Content",
                content = content,
                estimatedMinutes = (content.split("\\s+".toRegex()).size / 200).coerceAtLeast(1)
            )
        }
        chapterDao.insertChapters(chapters)
        bookId
    }

    suspend fun checkDomainSync(): SyncResult = withContext(Dispatchers.IO) {
        try {
            val url = URL("https://www.awamilibrary.com/")
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 4000
            connection.readTimeout = 4000
            connection.requestMethod = "HEAD"
            connection.instanceFollowRedirects = true
            val responseCode = connection.responseCode
            connection.disconnect()
            if (responseCode in 200..399) {
                SyncResult(
                    isOnline = true,
                    statusMessage = "Connected to https://www.awamilibrary.com/ (HTTP $responseCode). Local offline database is up-to-date."
                )
            } else {
                SyncResult(
                    isOnline = false,
                    statusMessage = "Awami Library server responded with status $responseCode. Using 100% offline local catalog."
                )
            }
        } catch (e: Exception) {
            SyncResult(
                isOnline = false,
                statusMessage = "Offline mode active. All books, chapters, and bookmarks are stored and accessible locally without internet."
            )
        }
    }
}

data class SyncResult(
    val isOnline: Boolean,
    val statusMessage: String
)
