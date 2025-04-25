package com.app.sudoku.presentation.screens.sudoku.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun SudokuBoard(
    puzzle: List<List<Int?>>,
    updatePuzzleCell: (row: Int, col: Int, value: Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        puzzle.forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                row.forEachIndexed { colIndex, cell ->
                    SudokuCell(
                        row = rowIndex,
                        col = colIndex,
                        value = cell,
                        updatePuzzleCell = updatePuzzleCell,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}
