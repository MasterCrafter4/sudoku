package com.app.sudoku.presentation.screens.sudoku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.sudoku.domain.usecase.GetSudokuPuzzleUseCase
import com.app.sudoku.presentation.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// SudokuViewModel.kt
@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val getSudokuPuzzleUseCase: GetSudokuPuzzleUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SudokuUiState())
    val uiState: StateFlow<SudokuUiState> = _uiState.asStateFlow()

    init {
        loadPuzzle(9, 9, "easy")
    }

    fun loadPuzzle(width: Int, height: Int, difficulty: String) {
        viewModelScope.launch {
            getSudokuPuzzleUseCase(width, height, difficulty).collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading ->
                            state.copy(isLoading = true, error = null)
                        is Result.Success ->
                            state.copy(
                                puzzle = result.data.puzzle,
                                originalPuzzle = result.data.puzzle,
                                solution = result.data.solution,
                                width = result.data.width,
                                height = result.data.height,
                                difficulty = result.data.difficulty,
                                isLoading = false,
                                error = null,
                                completed = false,
                                selectedCell = null
                            )
                        is Result.Error ->
                            state.copy(isLoading = false, error = result.exception.message)
                    }
                }
            }
        }
    }

    fun updateCell(row: Int, col: Int, value: Int?) {
        _uiState.update { state ->
            if (state.originalPuzzle[row][col] != null) {
                return@update state
            }

            val updatedGrid = state.puzzle.mapIndexed { r, rowList ->
                rowList.mapIndexed { c, cell ->
                    if (r == row && c == col) value else cell
                }
            }
            val completed = updatedGrid == state.solution
            state.copy(puzzle = updatedGrid, completed = completed)
        }
    }

    fun onCellSelected(row: Int, col: Int) {
        _uiState.update { state ->
            if (state.originalPuzzle[row][col] == null) {
                state.copy(selectedCell = Pair(row, col))
            } else {
                state
            }
        }
    }

    fun onNumberSelected(number: Int) {
        _uiState.value.selectedCell?.let { (row, col) ->
            updateCell(row, col, number)
        }
    }

    fun restartPuzzle() {
        loadPuzzle(uiState.value.width, uiState.value.height, uiState.value.difficulty)
    }

    fun newPuzzle() {
        loadPuzzle(uiState.value.width, uiState.value.height, uiState.value.difficulty)
    }
}