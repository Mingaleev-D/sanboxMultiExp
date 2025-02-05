package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import org.example.project.domain.repository.CharacterRepository
import org.example.project.ui.navigation.NavGraphSetup
import org.example.project.ui.pages.details.CharacterDetailsPage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
       // val characterRepository: CharacterRepository = koinInject()
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
