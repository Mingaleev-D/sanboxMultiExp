package org.example.project.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Column(
           modifier = Modifier.fillMaxSize(),
    ) {
        Text(
               text = "Home"
        )
    }
}
