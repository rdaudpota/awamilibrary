package com.example.ui.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.entity.BookEntity
import com.example.data.local.entity.BookmarkEntity
import com.example.data.local.entity.ChapterEntity
import com.example.data.local.entity.HighlightEntity
import com.example.data.repository.AwamiLibraryRepository
import com.example.domain.model.LineSpacing
import com.example.domain.model.ReaderFontFamily
import com.example.domain.model.ReaderSettings
import com.example.domain.model.ReaderTheme
import com.example.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale

class AwamiLibraryViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val repository: AwamiLibraryRepository
    private var tts: TextToSpeech? = null
    private var isTtsInitialized = false

    init {
        val database = AppDatabase.getDatabase(application, viewModelScope)
        repository = AwamiLibraryRepository(database)
        tts = TextToSpeech(application, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isTtsInitialized = true
            tts?.language = Locale.ENGLISH
        }
    }

    // Navigation Stack
    private val _screenStack = MutableStateFlow<List<Screen>>(listOf(Screen.Home))
    val currentScreen: StateFlow<Screen> = _screenStack
        .combine(flowOf(Unit)) { stack, _ -> stack.lastOrNull() ?: Screen.Home }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Screen.Home)

    fun navigateTo(screen: Screen) {
        val currentList = _screenStack.value.toMutableList()
        currentList.add(screen)
        _screenStack.value = currentList
    }

    fun navigateBack(): Boolean {
        val currentList = _screenStack.value.toMutableList()
        if (currentList.size > 1) {
            currentList.removeAt(currentList.lastIndex)
            _screenStack.value = currentList
            stopTts()
            return true
        }
        return false
    }

    // Filter and Search states
    val searchQuery = MutableStateFlow("")
    val selectedCategory = MutableStateFlow("All")
    val selectedLanguage = MutableStateFlow("All")

    val allBooks: StateFlow<List<BookEntity>> = repository.allBooks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteBooks: StateFlow<List<BookEntity>> = repository.favoriteBooks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val recentlyReadBooks: StateFlow<List<BookEntity>> = repository.recentlyReadBooks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val downloadedBooks: StateFlow<List<BookEntity>> = repository.downloadedBooks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allBookmarks: StateFlow<List<BookmarkEntity>> = repository.allBookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allHighlights: StateFlow<List<HighlightEntity>> = repository.allHighlights
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val filteredBooks: StateFlow<List<BookEntity>> = combine(
        allBooks,
        searchQuery,
        selectedCategory,
        selectedLanguage
    ) { books, query, category, language ->
        books.filter { book ->
            val matchesQuery = query.isBlank() ||
                book.title.contains(query, ignoreCase = true) ||
                book.titleNative.contains(query, ignoreCase = true) ||
                book.author.contains(query, ignoreCase = true) ||
                book.authorNative.contains(query, ignoreCase = true) ||
                book.description.contains(query, ignoreCase = true)

            val matchesCategory = category == "All" || book.category.contains(category, ignoreCase = true)
            val matchesLanguage = language == "All" || book.language.contains(language, ignoreCase = true)

            matchesQuery && matchesCategory && matchesLanguage
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Active Book Selection
    private val _activeBookId = MutableStateFlow<String?>(null)
    val activeBookId: StateFlow<String?> = _activeBookId.asStateFlow()

    val activeBook: StateFlow<BookEntity?> = _activeBookId
        .flatMapLatest { id ->
            if (id != null) repository.getBookById(id) else flowOf(null)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val activeChapters: StateFlow<List<ChapterEntity>> = _activeBookId
        .flatMapLatest { id ->
            if (id != null) repository.getChaptersForBook(id) else flowOf(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Active Chapter
    val activeChapterIndex = MutableStateFlow(0)

    val activeChapter: StateFlow<ChapterEntity?> = combine(
        _activeBookId,
        activeChapterIndex
    ) { bookId, index ->
        if (bookId != null) {
            repository.getChapterOnce(bookId, index)
        } else null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val isCurrentChapterBookmarked: StateFlow<Boolean> = combine(
        _activeBookId,
        activeChapterIndex,
        allBookmarks
    ) { bookId, index, bookmarks ->
        if (bookId == null) false
        else bookmarks.any { it.bookId == bookId && it.chapterIndex == index }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // Reader Settings
    private val _readerSettings = MutableStateFlow(ReaderSettings())
    val readerSettings: StateFlow<ReaderSettings> = _readerSettings.asStateFlow()

    fun updateFontSize(newSize: Float) {
        _readerSettings.value = _readerSettings.value.copy(fontSizeSp = newSize.coerceIn(14f, 32f))
    }

    fun updateReaderTheme(newTheme: ReaderTheme) {
        _readerSettings.value = _readerSettings.value.copy(theme = newTheme)
    }

    fun updateReaderFont(newFont: ReaderFontFamily) {
        _readerSettings.value = _readerSettings.value.copy(fontFamily = newFont)
    }

    fun updateLineSpacing(spacing: LineSpacing) {
        _readerSettings.value = _readerSettings.value.copy(lineSpacing = spacing)
    }

    // Sync state
    private val _syncStatus = MutableStateFlow("Offline Mode Active • All books stored locally")
    val syncStatus: StateFlow<String> = _syncStatus.asStateFlow()

    private val _isCheckingSync = MutableStateFlow(false)
    val isCheckingSync: StateFlow<Boolean> = _isCheckingSync.asStateFlow()

    fun openBook(bookId: String) {
        _activeBookId.value = bookId
        navigateTo(Screen.BookDetail(bookId))
    }

    fun openReader(bookId: String, chapterIndex: Int = 0) {
        _activeBookId.value = bookId
        activeChapterIndex.value = chapterIndex
        viewModelScope.launch {
            val chapters = repository.getChaptersForBook(bookId)
            val book = repository.getBookByIdOnce(bookId)
            val total = book?.totalChapters ?: 1
            val progressPercent = ((chapterIndex + 1).toFloat() / total.coerceAtLeast(1)) * 100f
            repository.updateReadingProgress(bookId, chapterIndex, progressPercent)
        }
        navigateTo(Screen.Reader(bookId, chapterIndex))
    }

    fun setChapter(index: Int) {
        stopTts()
        val bookId = _activeBookId.value ?: return
        activeChapterIndex.value = index
        viewModelScope.launch {
            val book = repository.getBookByIdOnce(bookId)
            val total = book?.totalChapters ?: 1
            val progressPercent = ((index + 1).toFloat() / total.coerceAtLeast(1)) * 100f
            repository.updateReadingProgress(bookId, index, progressPercent)
        }
    }

    fun toggleFavorite(book: BookEntity) {
        viewModelScope.launch {
            repository.toggleFavorite(book.id, book.isFavorite)
        }
    }

    fun toggleDownload(book: BookEntity) {
        viewModelScope.launch {
            repository.toggleDownload(book.id, book.isDownloaded)
        }
    }

    fun toggleBookmarkCurrentChapter(note: String = "") {
        val book = activeBook.value ?: return
        val chapter = activeChapter.value ?: return
        val isBookmarked = isCurrentChapterBookmarked.value

        viewModelScope.launch {
            if (isBookmarked) {
                // Find existing bookmark and delete
                val existing = allBookmarks.value.firstOrNull {
                    it.bookId == book.id && it.chapterIndex == activeChapterIndex.value
                }
                if (existing != null) {
                    repository.deleteBookmark(existing.id)
                }
            } else {
                val excerpt = chapter.content.take(160).replace("\n", " ").trim() + "..."
                repository.addBookmark(
                    bookId = book.id,
                    bookTitle = book.title,
                    chapterIndex = activeChapterIndex.value,
                    chapterTitle = chapter.title,
                    excerpt = excerpt,
                    note = note
                )
            }
        }
    }

    fun deleteBookmark(bookmarkId: Long) {
        viewModelScope.launch {
            repository.deleteBookmark(bookmarkId)
        }
    }

    fun saveHighlight(text: String, note: String, colorTag: String) {
        val bookId = _activeBookId.value ?: return
        val chapterIdx = activeChapterIndex.value
        viewModelScope.launch {
            repository.addHighlight(
                bookId = bookId,
                chapterIndex = chapterIdx,
                text = text,
                note = note,
                colorTag = colorTag
            )
        }
    }

    fun deleteHighlight(highlightId: Long) {
        viewModelScope.launch {
            repository.deleteHighlight(highlightId)
        }
    }

    // TTS offline reading aloud
    fun toggleTtsPlayback() {
        if (_readerSettings.value.isTtsPlaying) {
            stopTts()
        } else {
            val chapter = activeChapter.value ?: return
            startTts(chapter.content)
        }
    }

    private fun startTts(text: String) {
        if (isTtsInitialized && tts != null) {
            tts?.setSpeechRate(_readerSettings.value.speechRate)
            // Limit chunk size for speech engine
            val speakableText = text.replace(Regex("\\[.*?\\]"), "") // skip bracket notes
                .take(3000)
            tts?.speak(speakableText, TextToSpeech.QUEUE_FLUSH, null, "AwamiLibraryReader")
            _readerSettings.value = _readerSettings.value.copy(isTtsPlaying = true)
        }
    }

    fun stopTts() {
        if (tts != null) {
            tts?.stop()
            _readerSettings.value = _readerSettings.value.copy(isTtsPlaying = false)
        }
    }

    fun checkDomainSync() {
        viewModelScope.launch {
            _isCheckingSync.value = true
            _syncStatus.value = "Checking https://www.awamilibrary.com/ server..."
            val result = repository.checkDomainSync()
            _syncStatus.value = result.statusMessage
            _isCheckingSync.value = false
        }
    }

    fun importCustomBook(
        title: String,
        author: String,
        category: String,
        language: String,
        description: String,
        content: String
    ) {
        viewModelScope.launch {
            // Split content into chapters if there are markers or long text
            val chapterTexts = if (content.contains("---")) {
                content.split("---").filter { it.isNotBlank() }
            } else if (content.contains("\n\n\n")) {
                content.split("\n\n\n").filter { it.isNotBlank() }
            } else {
                listOf(content)
            }
            val chapterTitles = chapterTexts.mapIndexed { idx, _ -> "Chapter ${idx + 1}" }

            val bookId = repository.addCustomBook(
                title = title,
                titleNative = title,
                author = author,
                authorNative = author,
                category = category,
                language = language,
                description = description,
                chapterTitles = chapterTitles,
                chapterContents = chapterTexts
            )
            openBook(bookId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
