package com.app.sudoku.presentation.screens.sudoku.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Suppress("ktlint:standard:function-naming")
@Composable
fun SudokuCell(
    row: Int,
    col: Int,
    value: Int?,
    updatePuzzleCell: (row: Int, col: Int, value: Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value?.toString() ?: "",
        onValueChange = { newValue ->
            val intValue = newValue.toIntOrNull()
            if (intValue != null || newValue.isEmpty()) {
                updatePuzzleCell(row, col, if (newValue.isEmpty()) null else intValue)
            }
        },
        enabled = false,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        ),
        modifier = modifier.fillMaxSize()
    )
}
