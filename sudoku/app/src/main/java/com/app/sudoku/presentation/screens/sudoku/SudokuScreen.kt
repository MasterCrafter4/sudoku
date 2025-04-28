package com.app.sudoku.presentation.screens.sudoku


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.sudoku.presentation.screens.sudoku.components.SudokuBoard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SudokuScreen(
    viewModel: SudokuViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        viewModel.newPuzzle()
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Button(onClick = { viewModel.restartPuzzle() }) {
                    Text("Reiniciar")
                }
                Button(onClick = { viewModel.newPuzzle() }) {
                    Text("Nueva Partida")
                }
            }

            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(
                    "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.error
                )
                else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        SudokuBoard(
                            puzzle = state.puzzle,
                            originalPuzzle = state.originalPuzzle,
                            selectedCell = state.selectedCell,
                            onCellClick = { r, c ->
                                viewModel.onCellSelected(r, c)
                                focusRequester.requestFocus()
                            },
                            modifier = Modifier.fillMaxSize()
                        )

                        BasicTextField(
                            value = "",
                            onValueChange = { input ->
                                input.firstOrNull()?.digitToIntOrNull()?.let { number ->
                                    if (number in 1..9) {
                                        viewModel.onNumberSelected(number)
                                    }
                                }
                            },
                            modifier = Modifier
                                .size(1.dp)
                                .focusRequester(focusRequester),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            singleLine = true
                        )
                    }

                    if (state.completed) {
                        Text(
                            "¡Completado correctamente!",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(state.selectedCell) {
        if (state.selectedCell != null) {
            focusRequester.requestFocus()
        }
    }
}