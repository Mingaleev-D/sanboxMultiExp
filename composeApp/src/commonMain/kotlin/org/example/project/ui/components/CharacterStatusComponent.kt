package org.example.project.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.domain.model.CharacterStatus
import org.example.project.ui.theme.RickTextPrimary

@Composable
fun CharacterStatusComponent(characterStatus: CharacterStatus) {
    Row(
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .border(
                      width = 1.dp,
                      color = characterStatus.asColor(),
                      shape = RoundedCornerShape(12.dp)
               )
               .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
               text = "Status: ${characterStatus.displayName}",
               fontSize = 20.sp,
               color = RickTextPrimary
        )
    }
}

fun CharacterStatus.asColor(): Color {
    return when (this) {
        CharacterStatus.Alive -> Color.Green
        CharacterStatus.Dead -> Color.Red
        CharacterStatus.Unknown -> Color.Yellow
    }
}
