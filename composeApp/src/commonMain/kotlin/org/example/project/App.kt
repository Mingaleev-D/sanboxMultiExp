package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.ui.components.NavigationType
import org.example.project.ui.components.getNavigationType
import org.example.project.ui.pages.HomePage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(windowWidthSizeClass: WindowWidthSizeClass) {
    MaterialTheme {
        HomePage(
               navigationType = getNavigationType(windowWidthSizeClass),
        )
    }
}
