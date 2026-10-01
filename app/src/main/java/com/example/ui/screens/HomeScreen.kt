package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.entity.BookEntity
import com.example.ui.components.BookCoverCard
import com.example.ui.components.ImportBookDialog
import com.example.ui.navigation.Screen
import com.example.ui.theme.AwamiAmber
import com.example.ui.theme.AwamiAmberLight
import com.example.ui.theme.AwamiEmerald
import com.example.ui.theme.AwamiEmeraldLight
import com.example.ui.viewmodel.AwamiLibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AwamiLibraryViewModel,
    modifier: Modifier = Modifier
) {
    val filteredBooks by viewModel.filteredBooks.collectAsStateWithLifecycle()
    val allBooks by viewModel.allBooks.collectAsStateWithLifecycle()
    val recentlyReadBooks by viewModel.recentlyReadBooks.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val selectedLanguage by viewModel.selectedLanguage.collectAsStateWithLifecycle()

    var showImportDialog by remember { mutableStateOf(false) }

    val categories = listOf("All", "Poetry", "Classical", "Philosophy", "History", "Civics", "Lore")
    val languages = listOf("All", "Sindhi", "Urdu", "English")

    val continueBook = recentlyReadBooks.firstOrNull { it.readingProgressPercent > 0f }

    Scaffold(
        modifier = modifier.testTag("home_screen"),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Awami Library",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = Color(0x33FFFFFF),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "عوامي لائبريري",
                                    color = Color(0xFFFFE0BE),
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "https://www.awamilibrary.com/ • 100% Offline Reader",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD4EEDF)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AwamiEmerald,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = { viewModel.navigateTo(Screen.Bookmarks) },
                        modifier = Modifier.testTag("nav_bookmarks_btn")
                    ) {
                        Icon(Icons.Default.Bookmark, contentDescription = "Saved Bookmarks")
                    }
                    IconButton(
                        onClick = { viewModel.navigateTo(Screen.Settings) },
                        modifier = Modifier.testTag("nav_settings_btn")
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings & Sync")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showImportDialog = true },
                containerColor = AwamiAmber,
                contentColor = Color.White,
                modifier = Modifier.testTag("add_book_fab")
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Offline Book")
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 12.dp,
                bottom = innerPadding.calculateBottomPadding() + 80.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Offline Badge & Search Bar
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column {
                    // Offline Status Banner
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CloudDone,
                                contentDescription = "Offline Ready",
                                tint = AwamiEmerald,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Offline Mode Active • ${allBooks.size} Books Cached Locally",
                                style = MaterialTheme.typography.labelMedium,
                                color = AwamiEmerald,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Search input
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.searchQuery.value = it },
                        placeholder = { Text("Search by title, author, or language...") },
                        leadingIcon = {
                            Icon(Icons.Outlined.Search, contentDescription = "Search", tint = AwamiEmerald)
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.searchQuery.value = "" }) {
                                    Text("✕", color = AwamiEmerald)
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AwamiEmerald,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("home_search_input")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Category filters
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(categories) { cat ->
                            FilterChip(
                                selected = selectedCategory == cat,
                                onClick = { viewModel.selectedCategory.value = cat },
                                label = { Text(cat) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = AwamiEmerald,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Language filters
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(languages) { lang ->
                            FilterChip(
                                selected = selectedLanguage == lang,
                                onClick = { viewModel.selectedLanguage.value = lang },
                                label = {
                                    Text(
                                        when (lang) {
                                            "Sindhi" -> "Sindhi (سنڌي)"
                                            "Urdu" -> "Urdu (اردو)"
                                            "English" -> "English"
                                            else -> "All Languages"
                                        }
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = AwamiAmber,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }
                }
            }

            // Continue Reading Shelf if available
            if (continueBook != null && searchQuery.isBlank() && selectedCategory == "All") {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { viewModel.openReader(continueBook.id, continueBook.currentChapterIndex) }
                            .testTag("continue_reading_card"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(continueBook.coverColorHex)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.AutoStories,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "CONTINUE READING",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = AwamiAmber,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = continueBook.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Chapter ${continueBook.currentChapterIndex + 1} of ${continueBook.totalChapters} • ${continueBook.readingProgressPercent.toInt()}% completed",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                LinearProgressIndicator(
                                    progress = { continueBook.readingProgressPercent / 100f },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(4.dp)
                                        .clip(RoundedCornerShape(2.dp)),
                                    color = AwamiEmerald
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            FilledIconButtonSample(
                                onClick = { viewModel.openReader(continueBook.id, continueBook.currentChapterIndex) }
                            )
                        }
                    }
                }
            }

            // Section Header
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (searchQuery.isNotEmpty()) "Search Results (${filteredBooks.size})"
                        else if (selectedCategory != "All") "$selectedCategory (${filteredBooks.size})"
                        else "Library Books (${filteredBooks.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "100% Offline",
                        style = MaterialTheme.typography.labelSmall,
                        color = AwamiEmerald,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Book Cards
            items(filteredBooks, key = { it.id }) { book ->
                BookCoverCard(
                    book = book,
                    onClick = { viewModel.openBook(book.id) },
                    onFavoriteToggle = { viewModel.toggleFavorite(book) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    if (showImportDialog) {
        ImportBookDialog(
            onDismiss = { showImportDialog = false },
            onImport = { title, author, category, language, description, content ->
                viewModel.importCustomBook(title, author, category, language, description, content)
            }
        )
    }
}

@Composable
private fun FilledIconButtonSample(onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = AwamiEmerald,
        modifier = Modifier.size(38.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "Resume",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
