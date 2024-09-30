package com.dova.plateauAssistant.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dova.plateauAssistant.domain.Items
import com.dova.plateauAssistant.ui.composants.Case
import com.dova.plateauAssistant.ui.composants.DraggableItem

@Composable
fun PlateauScreen() {
    val viewModel: PlateauViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Column{
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(Items.entries.size) { id ->
                DraggableItem(id)
            }
        }
        Spacer(Modifier.weight(1f))
        Column(
            Modifier.fillMaxWidth().padding(horizontal = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                for (index in 0..<uiState.value.nbCases step 2) {
                    Case(
                        Modifier.weight(1f),
                        index,
                        uiState.value.plateau[index],
                        { index, value -> viewModel.dropOnPlateau(index, value) },
                        { index -> viewModel.resetCase(index) }
                    )
                }
            }
            Row {
                for (index in 1..uiState.value.nbCases step 2) {
                    Case(
                        Modifier.weight(1f),
                        index,
                        uiState.value.plateau[index],
                        { index, value -> viewModel.dropOnPlateau(index, value) },
                        { index -> viewModel.resetCase(index) }
                    )
                }
            }
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun Previewzer() {
    PlateauScreen()
}