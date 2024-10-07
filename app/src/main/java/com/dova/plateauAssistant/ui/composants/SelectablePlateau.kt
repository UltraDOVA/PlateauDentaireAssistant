package com.dova.plateauAssistant.ui.composants

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dova.plateauAssistant.data.entities.Plateau

@Composable
fun SelectablePlateau(
    plateau: Plateau,
    navTo: (Int) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f
    )
    Card(
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            Modifier.clickable { isExpanded = !isExpanded }.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(plateau.name)
            Spacer(Modifier.weight(1f))
            if (isExpanded)
                Icon(
                    Icons.Default.Create,
                    null,
                    Modifier.clickable { navTo(plateau.id) }
                )
            else
                Icon(
                    Icons.Default.PlayArrow,
                    null,
                    Modifier.clickable { navTo(plateau.id) }
                )
            Icon(Icons.Default.ArrowDropDown, null, Modifier.graphicsLayer(rotationZ = rotation))
        }
        AnimatedVisibility(isExpanded) {
            PlateauComposable(plateau, false)
        }
    }
}

@Preview
@Composable
fun Preview() {
    SelectablePlateau(
        Plateau(
            name = "current",
            index = 0,
            nbCases = 8,
            nbLines = 2,
            values = listOf(-1,-1,-1,-1,-1,-1,-1,-1)
        )
    ) { }
}