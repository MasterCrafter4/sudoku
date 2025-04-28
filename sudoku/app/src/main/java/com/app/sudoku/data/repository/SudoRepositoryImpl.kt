package com.app.sudoku.data.repository

import com.app.sudoku.data.mapper.toDomain
import com.app.sudoku.data.remote.api.SudokuApi
import com.app.sudoku.domain.model.SudokuPuzzle
import com.app.sudoku.domain.repository.SudokuRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SudokuRepositoryImpl
@Inject
constructor(
    private val api: SudokuApi,
) : SudokuRepository {
    override suspend fun getPuzzle(
        width: Int,
        height: Int,
        difficulty: String,
    ): SudokuPuzzle {
        return api.getSudokuPuzzle(width, height, difficulty).toDomain(
            width = width,
            height = height,
            difficulty = difficulty,
        )
    }
}
