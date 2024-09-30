package com.dova.plateauAssistant.ui.composants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Case(
    modifier: Modifier,
    index: Int,
    value: Int,
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
        color = Color.LightGray,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        if (value != -1) {
            DraggableItem(value) { resetCase(index) }
        }
    }
}