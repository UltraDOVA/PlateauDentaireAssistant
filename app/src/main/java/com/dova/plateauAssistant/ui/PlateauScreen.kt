package com.dova.plateauAssistant.ui

import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dova.plateauAssistant.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlateauScreen() {
    val viewModel: PlateauViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().background(Color.Green)) {
        Image(
            painterResource(R.drawable.dovoooo),
            null,
            Modifier.dragAndDropSource {
                detectTapGestures(onLongPress = {
                    startTransfer(
                        DragAndDropTransferData(
                            ClipData.newPlainText(
                                "image Url", "url"
                            )
                        )
                    )
                })
            }
        )
        Spacer(Modifier.weight(1f))
        Column(
            Modifier.fillMaxWidth().padding(horizontal = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                for (index in 0..<uiState.value.nbCases step 2) {
                    Log.d("DOVA", "je suis $index")
                    Surface(
                        Modifier.height(80.dp).width(300.dp).weight(1f)
                            .dragAndDropTarget(
                                { true },
                                target = object: DragAndDropTarget {
                                    override fun onDrop(event: DragAndDropEvent): Boolean {
                                        viewModel.dropOnPlateau(index,1)
                                        return true
                                    }
                                }
                            ),
                        color = Color.LightGray,
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        if (uiState.value.plateau[index] == 1)
                            Image(painterResource(R.drawable.dovoooo), null)
                    }
                }
            }
            Row {
                for (index in 1..uiState.value.nbCases step 2) {
                    Surface(
                        Modifier.height(80.dp).width(300.dp).weight(1f)
                            .dragAndDropTarget(
                                { true },
                                target = object: DragAndDropTarget {
                                    override fun onDrop(event: DragAndDropEvent): Boolean {
                                        viewModel.dropOnPlateau(index,1)
                                        return true
                                    }
                                }
                            ),
                        color = Color.LightGray,
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        if (uiState.value.plateau[index] == 1)
                            Image(painterResource(R.drawable.dovoooo), null)
                    }
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