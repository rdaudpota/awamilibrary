package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.ReaderFontFamily
import com.example.domain.model.ReaderTheme
import com.example.ui.components.AddBookmarkDialog
import com.example.ui.components.ReaderSettingsSheet
import com.example.ui.theme.AwamiAmber
import com.example.ui.theme.AwamiEmerald
import com.example.ui.theme.ReaderNightBg
import com.example.ui.theme.ReaderNightText
import com.example.ui.theme.ReaderOledBg
import com.example.ui.theme.ReaderOledText
import com.example.ui.theme.ReaderPaperBg
import com.example.ui.theme.ReaderPaperText
import com.example.ui.theme.ReaderSepiaBg
import com.example.ui.theme.ReaderSepiaText
import com.example.ui.viewmodel.AwamiLibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    viewModel: AwamiLibraryViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.stopTts()
        viewModel.navigateBack()
    }

    val book by viewModel.activeBook.collectAsStateWithLifecycle()
    val chapters by viewModel.activeChapters.collectAsStateWithLifecycle()
    val currentChapter by viewModel.activeChapter.collectAsStateWithLifecycle()
    val currentChapterIdx by viewModel.activeChapterIndex.collectAsStateWithLifecycle()
    val isBookmarked by viewModel.isCurrentChapterBookmarked.collectAsStateWithLifecycle()
    val readerSettings by viewModel.readerSettings.collectAsStateWithLifecycle()

    var showSettingsSheet by remember { mutableStateOf(false) }
    var showBookmarkDialog by remember { mutableStateOf(false) }
    var showChapterMenu by remember { mutableStateOf(false) }

    // Color resolution for reader theme
    val (readerBg, readerTextColor) = when (readerSettings.theme) {
        ReaderTheme.PARCHMENT -> Pair(ReaderPaperBg, ReaderPaperText)
        ReaderTheme.SEPIA -> Pair(ReaderSepiaBg, ReaderSepiaText)
        ReaderTheme.DAY -> Pair(Color.White, Color(0xFF1E1E1E))
        ReaderTheme.NIGHT -> Pair(ReaderNightBg, ReaderNightText)
        ReaderTheme.OLED -> Pair(ReaderOledBg, ReaderOledText)
    }

    // Font resolution
    val fontFamily = when (readerSettings.fontFamily) {
        ReaderFontFamily.SERIF -> FontFamily.Serif
        ReaderFontFamily.SANS -> FontFamily.SansSerif
        ReaderFontFamily.ARABIC_NASKH -> FontFamily.Default
    }

    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.testTag("reader_screen"),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = book?.title ?: "Awami Reader",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Ch ${currentChapterIdx + 1} of ${chapters.size}: ${currentChapter?.title ?: ""}",
                            style = MaterialTheme.typography.labelSmall,
                            color = readerTextColor.copy(alpha = 0.7f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModel.stopTts()
                            viewModel.navigateBack()
                        },
                        modifier = Modifier.testTag("reader_back_btn")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = readerTextColor
                        )
                    }
                },
                actions = {
                    // Chapter selector
                    Box {
                        IconButton(onClick = { showChapterMenu = true }) {
                            Icon(
                                Icons.Default.FormatListNumbered,
                                contentDescription = "Chapter list",
                                tint = readerTextColor
                            )
                        }
                        DropdownMenu(
                            expanded = showChapterMenu,
                            onDismissRequest = { showChapterMenu = false }
                        ) {
                            chapters.forEachIndexed { idx, ch ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = "${idx + 1}. ${ch.titleNative} (${ch.title})",
                                            fontWeight = if (idx == currentChapterIdx) FontWeight.Bold else FontWeight.Normal,
                                            color = if (idx == currentChapterIdx) AwamiEmerald else MaterialTheme.colorScheme.onSurface
                                        )
                                    },
                                    onClick = {
                                        showChapterMenu = false
                                        viewModel.setChapter(idx)
                                    }
                                )
                            }
                        }
                    }

                    // TTS Offline Voice Read Aloud
                    IconButton(
                        onClick = { viewModel.toggleTtsPlayback() },
                        modifier = Modifier.testTag("reader_tts_btn")
                    ) {
                        Icon(
                            imageVector = if (readerSettings.isTtsPlaying) Icons.Default.Stop else Icons.Default.Headphones,
                            contentDescription = if (readerSettings.isTtsPlaying) "Stop audio" else "Listen aloud offline",
                            tint = if (readerSettings.isTtsPlaying) AwamiAmber else readerTextColor
                        )
                    }

                    // Bookmark toggle
                    IconButton(
                        onClick = {
                            if (isBookmarked) {
                                viewModel.toggleBookmarkCurrentChapter()
                            } else {
                                showBookmarkDialog = true
                            }
                        },
                        modifier = Modifier.testTag("reader_bookmark_btn")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) AwamiAmber else readerTextColor
                        )
                    }

                    // Reader Settings
                    IconButton(
                        onClick = { showSettingsSheet = true },
                        modifier = Modifier.testTag("reader_style_btn")
                    ) {
                        Icon(
                            Icons.Default.FormatSize,
                            contentDescription = "Reader Appearance",
                            tint = readerTextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = readerBg,
                    titleContentColor = readerTextColor
                )
            )
        },
        bottomBar = {
            // Reader Bottom Control Bar
            Surface(
                color = readerBg,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    // Progress indicator
                    val progressFloat = ((currentChapterIdx + 1).toFloat() / chapters.size.coerceAtLeast(1))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Chapter ${currentChapterIdx + 1} of ${chapters.size}",
                            fontSize = 11.sp,
                            color = readerTextColor.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "${(progressFloat * 100).toInt()}% completed",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = AwamiEmerald
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = { progressFloat },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .clip(RoundedCornerShape(1.5.dp)),
                        color = AwamiAmber,
                        trackColor = readerTextColor.copy(alpha = 0.1f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Previous Chapter Button
                        OutlinedButton(
                            onClick = {
                                if (currentChapterIdx > 0) {
                                    viewModel.setChapter(currentChapterIdx - 1)
                                }
                            },
                            enabled = currentChapterIdx > 0,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.testTag("prev_chapter_btn")
                        ) {
                            Icon(Icons.Default.NavigateBefore, contentDescription = null)
                            Text("Prev")
                        }

                        // Audio Status if active
                        if (readerSettings.isTtsPlaying) {
                            Surface(
                                color = AwamiAmber.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "Reading Aloud...",
                                    color = AwamiAmber,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        // Next Chapter Button
                        Button(
                            onClick = {
                                if (currentChapterIdx < chapters.size - 1) {
                                    viewModel.setChapter(currentChapterIdx + 1)
                                }
                            },
                            enabled = currentChapterIdx < chapters.size - 1,
                            colors = ButtonDefaults.buttonColors(containerColor = AwamiEmerald),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.testTag("next_chapter_btn")
                        ) {
                            Text("Next")
                            Icon(Icons.Default.NavigateNext, contentDescription = null)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(readerBg)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                // Chapter Header
                if (currentChapter != null) {
                    val chap = currentChapter!!

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = chap.titleNative,
                            color = readerTextColor,
                            fontSize = (readerSettings.fontSizeSp + 6).sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = chap.title,
                            color = readerTextColor.copy(alpha = 0.85f),
                            fontSize = (readerSettings.fontSizeSp + 1).sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            textAlign = TextAlign.Center
                        )
                        if (chap.subtitle.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = chap.subtitle,
                                color = readerTextColor.copy(alpha = 0.65f),
                                fontSize = (readerSettings.fontSizeSp - 3).sp,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(
                            color = readerTextColor.copy(alpha = 0.15f),
                            thickness = 1.dp,
                            modifier = Modifier.width(80.dp)
                        )
                    }

                    // Main Reading Text
                    val lineSpacingMultiplier = readerSettings.lineSpacing.multiplier
                    val calculatedLineHeight = (readerSettings.fontSizeSp * lineSpacingMultiplier).sp

                    Text(
                        text = chap.content,
                        color = readerTextColor,
                        fontSize = readerSettings.fontSizeSp.sp,
                        fontFamily = fontFamily,
                        lineHeight = calculatedLineHeight,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("reader_content_text")
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // End of Chapter Divider & Action
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "•  •  •",
                                color = readerTextColor.copy(alpha = 0.4f),
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            if (currentChapterIdx < chapters.size - 1) {
                                Button(
                                    onClick = { viewModel.setChapter(currentChapterIdx + 1) },
                                    colors = ButtonDefaults.buttonColors(containerColor = AwamiEmerald)
                                ) {
                                    Text("Continue to Next Chapter")
                                    Icon(Icons.Default.NavigateNext, contentDescription = null)
                                }
                            } else {
                                Text(
                                    text = "You have completed this book!",
                                    color = AwamiEmerald,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }

    // Reader Settings Bottom Sheet
    if (showSettingsSheet) {
        ReaderSettingsSheet(
            settings = readerSettings,
            onUpdateFontSize = { viewModel.updateFontSize(it) },
            onUpdateTheme = { viewModel.updateReaderTheme(it) },
            onUpdateFont = { viewModel.updateReaderFont(it) },
            onUpdateLineSpacing = { viewModel.updateLineSpacing(it) },
            onDismiss = { showSettingsSheet = false }
        )
    }

    // Add Bookmark Dialog
    if (showBookmarkDialog && currentChapter != null) {
        AddBookmarkDialog(
            chapterTitle = currentChapter!!.title,
            onDismiss = { showBookmarkDialog = false },
            onConfirm = { note ->
                viewModel.toggleBookmarkCurrentChapter(note)
            }
        )
    }
}
