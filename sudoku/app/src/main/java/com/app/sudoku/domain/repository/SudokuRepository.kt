package com.app.sudoku.domain.repository

import com.app.sudoku.domain.model.SudokuPuzzle

interface SudokuRepository {
    suspend fun getPuzzle(
        width: Int,
        height: Int,
        difficulty: String
    ): SudokuPuzzle
}

