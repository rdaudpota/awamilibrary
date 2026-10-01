package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.LineSpacing
import com.example.domain.model.ReaderFontFamily
import com.example.domain.model.ReaderSettings
import com.example.domain.model.ReaderTheme
import com.example.ui.theme.AwamiAmber
import com.example.ui.theme.AwamiEmerald

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderSettingsSheet(
    settings: ReaderSettings,
    onUpdateFontSize: (Float) -> Unit,
    onUpdateTheme: (ReaderTheme) -> Unit,
    onUpdateFont: (ReaderFontFamily) -> Unit,
    onUpdateLineSpacing: (LineSpacing) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .testTag("reader_settings_sheet")
        ) {
            Text(
                text = "Reader Appearance",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Customize offline reading for day, sepia, or night",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Reading Theme Circles
            Text(
                text = "Theme: ${settings.theme.displayName}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ThemeOptionCircle(
                    label = "Parchment",
                    bgColor = Color(0xFFFAF8F2),
                    borderColor = Color(0xFFC0B8A8),
                    isSelected = settings.theme == ReaderTheme.PARCHMENT,
                    onClick = { onUpdateTheme(ReaderTheme.PARCHMENT) }
                )
                ThemeOptionCircle(
                    label = "Sepia",
                    bgColor = Color(0xFFF4ECD8),
                    borderColor = Color(0xFFB8782B),
                    isSelected = settings.theme == ReaderTheme.SEPIA,
                    onClick = { onUpdateTheme(ReaderTheme.SEPIA) }
                )
                ThemeOptionCircle(
                    label = "Day",
                    bgColor = Color(0xFFFFFFFF),
                    borderColor = Color(0xFFCCCCCC),
                    isSelected = settings.theme == ReaderTheme.DAY,
                    onClick = { onUpdateTheme(ReaderTheme.DAY) }
                )
                ThemeOptionCircle(
                    label = "Night",
                    bgColor = Color(0xFF1E2422),
                    borderColor = Color(0xFF4A5550),
                    isSelected = settings.theme == ReaderTheme.NIGHT,
                    onClick = { onUpdateTheme(ReaderTheme.NIGHT) }
                )
                ThemeOptionCircle(
                    label = "OLED",
                    bgColor = Color(0xFF000000),
                    borderColor = Color(0xFF333333),
                    isSelected = settings.theme == ReaderTheme.OLED,
                    onClick = { onUpdateTheme(ReaderTheme.OLED) }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Font Size Slider
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Text Size",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "${settings.fontSizeSp.toInt()} sp",
                    style = MaterialTheme.typography.labelMedium,
                    color = AwamiEmerald,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("A", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Slider(
                    value = settings.fontSizeSp,
                    onValueChange = onUpdateFontSize,
                    valueRange = 14f..30f,
                    steps = 7,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp)
                        .testTag("font_size_slider"),
                    colors = SliderDefaults.colors(
                        thumbColor = AwamiEmerald,
                        activeTrackColor = AwamiEmerald
                    )
                )
                Text("A", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Typography Style
            Text(
                text = "Font Family",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ReaderFontFamily.values().forEach { font ->
                    FilterChip(
                        selected = settings.fontFamily == font,
                        onClick = { onUpdateFont(font) },
                        label = { Text(font.displayName, fontSize = 12.sp) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Line Spacing
            Text(
                text = "Line Spacing",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LineSpacing.values().forEach { spacing ->
                    FilterChip(
                        selected = settings.lineSpacing == spacing,
                        onClick = { onUpdateLineSpacing(spacing) },
                        label = { Text(spacing.displayName, fontSize = 12.sp) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ThemeOptionCircle(
    label: String,
    bgColor: Color,
    borderColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(bgColor)
                .border(
                    width = if (isSelected) 3.dp else 1.dp,
                    color = if (isSelected) AwamiAmber else borderColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Aa",
                color = if (bgColor == Color.Black || bgColor == Color(0xFF1E2422)) Color.White else Color(0xFF2A2621),
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = if (isSelected) AwamiEmerald else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
