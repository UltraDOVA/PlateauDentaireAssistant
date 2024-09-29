package com.dova.plateauAssistant.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { NavBar(navController) }) { innerPadding ->
        NavHost(navController, startDestination = "plateau", modifier = Modifier.padding(innerPadding)) {
            composable("plateau") { PlateauScreen() }
            composable("saves") { SavedPlateaux() }
        }
    }
}

@Composable
fun NavBar(
    navController: NavHostController
) {
    Row(
        Modifier.fillMaxWidth().padding(10.dp).padding(bottom = 35.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = { navController.navigate("plateau") }) {
            Text("Plateau")
        }
        Button(onClick = { navController.navigate("saves") }) {
            Text("Saves")
        }
    }
}

@Preview
@Composable
fun Preview() {
    Navigation()
}