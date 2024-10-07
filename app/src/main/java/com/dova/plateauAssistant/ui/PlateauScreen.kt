package com.dova.plateauAssistant.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dova.plateauAssistant.domain.Items
import com.dova.plateauAssistant.ui.composants.AskNamePopOut
import com.dova.plateauAssistant.ui.composants.DraggableItem
import com.dova.plateauAssistant.ui.composants.PlateauComposable

@Composable
fun PlateauScreen() {
    val viewModel: PlateauViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedVisibility(uiState.value.askNameForSave) {
        AskNamePopOut(
            dismiss = { viewModel.dismissAskForName() },
            save = {name -> viewModel.savePlateau(name) }
        )

    }

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
            PlateauComposable(
                uiState.value.plateau,
                { index, value -> viewModel.dropOnPlateau(index, value) },
                { index -> viewModel.resetCase(index) }
            )
        }
        Spacer(Modifier.weight(1f))
        Button(onClick = { viewModel.askForName() }) {
            Text("Save")
        }
        Button(onClick = { viewModel.loadPlateau() }) {
            Text("Load")
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun Previewzer() {
    PlateauScreen()
}