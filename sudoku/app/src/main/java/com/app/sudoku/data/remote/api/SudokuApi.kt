package com.app.sudoku.data.remote.api

import com.app.sudoku.data.remote.dto.SudokuDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SudokuApi {
    @GET("sudokugenerate")
    suspend fun getSudokuPuzzle(
        @Query("width") width: Int,
        @Query("height") height: Int,
        @Query("difficulty") difficulty: String
    ): SudokuDto
}



