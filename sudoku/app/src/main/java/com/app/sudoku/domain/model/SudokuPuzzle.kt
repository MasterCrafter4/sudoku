package com.app.sudoku.domain.model

data class SudokuPuzzle(
    val puzzle: List<List<Int?>>,
    val solution: List<List<Int>>,
    val width: Int,
    val height: Int,
    val difficulty: String
)


