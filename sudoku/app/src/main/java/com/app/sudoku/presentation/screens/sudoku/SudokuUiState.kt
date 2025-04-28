package com.app.sudoku.presentation.screens.sudoku

data class SudokuUiState(
    val puzzle: List<List<Int?>> = emptyList(),
    val originalPuzzle: List<List<Int?>> = emptyList(),
    val solution: List<List<Int>> = emptyList(),
    val width: Int = 0,
    val height: Int = 0,
    val difficulty: String = "easy",
    val isLoading: Boolean = false,
    val error: String? = null,
    val completed: Boolean = false,
    val selectedCell: Pair<Int, Int>? = null
)

