package com.dova.plateauAssistant.ui.composants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dova.plateauAssistant.data.entities.Plateau
import com.dova.plateauAssistant.domain.Items


@Composable
fun PlateauComposable(
    plateau: Plateau,
    editable: Boolean,
    onDrop: (Int, Int) -> Unit = { _, _ -> },
    resetCase: (Int) -> Unit = {}
) {
    Column {
        for (line in 0..<plateau.nbLines) {
            Row {
                for (index in line..<plateau.nbCases step plateau.nbLines) {
                    Case(
                        Modifier.weight(1f),
                        index,
                        plateau.values[index],
                        editable,
                        onDrop,
                        resetCase
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Case(
    modifier: Modifier,
    index: Int,
    value: Int,
    editable: Boolean,
    onDrop: (Int, Int) -> Unit,
    resetCase: (Int) -> Unit
) {
    Surface(
        modifier.height(80.dp).width(300.dp)
            .dragAndDropTarget(
                { true },
                target = object: DragAndDropTarget {
                    override fun onDrop(event: DragAndDropEvent): Boolean {
                        onDrop(index, event.toAndroidDragEvent().clipData.getItemAt(0).text.toString().toInt())
                        return true
                    }
                }
            ),
        color = if (true) Color.LightGray else MaterialTheme.colorScheme.surface,
        border = if (true) BorderStroke(1.dp, Color.Black) else null
    ) {
        if (value != -1) {
            if (editable)
                DraggableItem(value) { resetCase(index) }
            else
                Image(painterResource(Items.entries[value].idDrawable), null)
        }
    }
}