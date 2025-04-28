package com.app.sudoku.data.mapper

import com.app.sudoku.data.remote.dto.SudokuDto
import com.app.sudoku.domain.model.SudokuPuzzle

fun SudokuDto.toDomain(
    width: Int,
    height: Int,
    difficulty: String
): SudokuPuzzle =
    SudokuPuzzle(
        puzzle = puzzle,
        solution = solution,
        width = width,
        height = height,
        difficulty = difficulty
    )

