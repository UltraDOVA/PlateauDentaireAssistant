package com.dova.plateauAssistant.ui.composants

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.res.painterResource
import com.dova.plateauAssistant.domain.Items

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableItem(
    id: Int,
    resetCase: () -> Unit = {}
) {
    val item = Items.entries[id]
    Image(
        painterResource(item.idDrawable),
        null,
        Modifier.dragAndDropSource {
            detectTapGestures(onLongPress = {
                resetCase()
                startTransfer(
                    DragAndDropTransferData(
                        ClipData.newPlainText(
                            item.name, id.toString()
                        )
                    )
                )
            })
        }
    )
}