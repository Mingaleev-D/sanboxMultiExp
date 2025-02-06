package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import org.example.project.ui.navigation.NavGraphSetup
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
               color = Color.Black
        ) {
            val navController = rememberNavController()
            NavGraphSetup(
                   navHostController = navController
            )
        }
    }
}
