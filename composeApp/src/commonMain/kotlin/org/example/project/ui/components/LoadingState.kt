package org.example.project.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.ui.theme.RickAction

@Composable
 fun LoadingState() {
    CircularProgressIndicator(
           modifier = Modifier
               .fillMaxSize()
               .padding(all = 128.dp),
           color = RickAction
    )
}
