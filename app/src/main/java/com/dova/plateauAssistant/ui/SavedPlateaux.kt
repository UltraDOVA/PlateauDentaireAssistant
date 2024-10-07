package com.dova.plateauAssistant.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dova.plateauAssistant.ui.composants.SelectablePlateau
import com.dova.plateauAssistant.ui.theme.Typography

@Composable
fun SavedPlateaux(
    navController: NavHostController
) {
    val viewModel: SavedPlateauxViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        Modifier.padding(horizontal = 30.dp).padding(top = 40.dp)
    ) {
        item {
            Text(
                "Plateaux enregistrés",
                Modifier.padding(15.dp).padding(start = 20.dp),
                style = Typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        items(uiState.value.customPlateaux.size) {
            SelectablePlateau(uiState.value.customPlateaux[it]) { idPlateau ->
                navController.navigate("plateau/$idPlateau")
            }
            Spacer(Modifier.height(10.dp))
        }

        item {
            Text(
                "Plateaux de cours",
                Modifier.padding(15.dp).padding(start = 20.dp),
                style = Typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}