package com.app.sudoku.domain.usecase

import com.app.sudoku.domain.model.SudokuPuzzle
import com.app.sudoku.domain.repository.SudokuRepository
import com.app.sudoku.presentation.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSudokuPuzzleUseCase
@Inject
constructor(
    private val repository: SudokuRepository,
) {
    operator fun invoke(
        width: Int,
        height: Int,
        difficulty: String
    ): Flow<Result<SudokuPuzzle>> =
        flow {
            try {
                emit(Result.Loading)
                val puzzle = repository.getPuzzle(width, height, difficulty)
                emit(Result.Success(puzzle))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}

