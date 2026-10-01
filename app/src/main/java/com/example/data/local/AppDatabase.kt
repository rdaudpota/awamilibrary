package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.local.dao.BookDao
import com.example.data.local.dao.BookmarkDao
import com.example.data.local.dao.ChapterDao
import com.example.data.local.dao.HighlightDao
import com.example.data.local.entity.BookEntity
import com.example.data.local.entity.BookmarkEntity
import com.example.data.local.entity.ChapterEntity
import com.example.data.local.entity.HighlightEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        BookEntity::class,
        ChapterEntity::class,
        BookmarkEntity::class,
        HighlightEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun chapterDao(): ChapterDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun highlightDao(): HighlightDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "awami_library_offline.db"
                )
                    .addCallback(DatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database)
                    }
                }
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        // Ensure database is populated if empty
                        if (database.bookDao().getBooksCount() == 0) {
                            populateDatabase(database)
                        }
                    }
                }
            }

            private suspend fun populateDatabase(database: AppDatabase) {
                val bookDao = database.bookDao()
                val chapterDao = database.chapterDao()
                bookDao.insertBooks(AwamiLibrarySeedData.getInitialBooks())
                chapterDao.insertChapters(AwamiLibrarySeedData.getInitialChapters())
            }
        }
    }
}
