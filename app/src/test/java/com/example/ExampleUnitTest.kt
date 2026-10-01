package com.example

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun seedBooks_hasAuthenticTitlesAndChapters() {
    val books = com.example.data.local.AwamiLibrarySeedData.getInitialBooks()
    val chapters = com.example.data.local.AwamiLibrarySeedData.getInitialChapters()

    assertTrue(books.isNotEmpty())
    assertTrue(chapters.isNotEmpty())
    assertTrue(books.any { it.id == "shah-jo-risalo" })
    assertTrue(books.any { it.id == "diwan-e-ghalib" })
    assertTrue(books.all { it.isDownloaded })
  }
}
