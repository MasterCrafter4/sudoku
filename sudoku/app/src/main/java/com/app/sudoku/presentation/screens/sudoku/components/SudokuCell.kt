package com.app.sudoku.presentation.screens.sudoku.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// SudokuCell.kt
@Composable
fun SudokuCell(
    row: Int,
    col: Int,
    value: Int?,
    isOriginal: Boolean,
    isSelected: Boolean,
    onCellClick: (row: Int, col: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        isOriginal -> Color.LightGray
        isSelected -> Color(0xFFE0E0E0)
        else -> Color.White
    }

    Box(
        modifier = modifier
            .border(1.dp, Color.Black)
            .clickable(enabled = !isOriginal) {
                onCellClick(row, col)
            }
            .background(backgroundColor)
    ) {
        if (value != null) {
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = if (isOriginal) Color.Black else Color.Red
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}