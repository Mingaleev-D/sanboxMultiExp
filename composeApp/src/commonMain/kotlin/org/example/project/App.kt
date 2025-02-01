package org.example.project

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.example.project.ui.navigation.HavGraphSetup
import org.example.project.ui.theme.SocialAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    SocialAppTheme {
        Scaffold {
            val navController = rememberNavController()
            HavGraphSetup(
                   navController = navController,
                   )
        }
    }
}
