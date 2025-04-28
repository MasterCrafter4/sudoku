package com.app.sudoku.presentation.screens.sudoku.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun SudokuBoard(
    puzzle: List<List<Int?>>,
    originalPuzzle: List<List<Int?>>,
    selectedCell: Pair<Int, Int>?,
    onCellClick: (row: Int, col: Int) -> Unit,
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
                        isOriginal = originalPuzzle[rowIndex][colIndex] != null,
                        isSelected = selectedCell?.first == rowIndex && selectedCell.second == colIndex,
                        onCellClick = onCellClick,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}