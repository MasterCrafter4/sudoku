package com.app.sudoku.presentation.screens.sudoku

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.sudoku.presentation.screens.sudoku.components.SudokuBoard

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SudokuScreen(
    viewModel: SudokuViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadPuzzle(width = 4, height = 4, difficulty = "easy")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Sudoku") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(
                    "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.error
                )
                else -> {
                    // BOX cuadrado que ocupa TODO el ancho y fuerza aspectRatio = 1:1
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
                        SudokuBoard(
                            puzzle = state.puzzle,
                            updatePuzzleCell = { r, c, v ->
                                viewModel.updateCell(r, c, v)
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    if (state.completed) {
                        Text(
                            "¡Completado correctamente!",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
                        )
                    }

                    Button(onClick = { viewModel.restartPuzzle() }) {
                        Text("Reiniciar")
                    }
                }
            }
        }
    }
}
