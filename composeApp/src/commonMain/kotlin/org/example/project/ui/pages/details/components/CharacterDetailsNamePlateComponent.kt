package org.example.project.ui.pages.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.example.project.domain.model.CharacterStatus
import org.example.project.ui.components.CharacterStatusComponent
import org.example.project.ui.theme.RickAction

@Composable
fun CharacterDetailsNamePlateComponent(name: String, status: CharacterStatus) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CharacterStatusComponent(characterStatus = status)
        Text(
               text = name,
               fontSize = 42.sp,
               lineHeight = 42.sp,
               fontWeight = FontWeight.Bold,
               color = RickAction
        )
    }
}
