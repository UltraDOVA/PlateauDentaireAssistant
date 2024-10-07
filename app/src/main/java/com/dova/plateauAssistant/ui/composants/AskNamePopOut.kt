package com.dova.plateauAssistant.ui.composants

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AskNamePopOut(
    dismiss: () -> Unit,
    save: (String) -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = dismiss,
        title = { Text("Entrez le nom de ce plateau") },
        text = { TextField(name, {name = it}) },
        confirmButton = { TextButton({ save(name) }) { Text("enregistrer") } },
        dismissButton = { TextButton(dismiss) { Text("annuler") } }
    )
}