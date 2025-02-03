package org.example.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.example.project.ui.navigation.NavGraphSetup
import org.example.project.ui.theme.SocialAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    SocialAppTheme {
        val navController = rememberNavController()
        NavGraphSetup(
               //modifier = Modifier.padding(paddingValues = paddingValue),
               navController = navController,
        )
//        Scaffold(
//               modifier = Modifier.fillMaxSize(),
//        ) { paddingValue ->
//
//        }
    }
}
