package org.example.project.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.ui.theme.Gray

@Composable
fun FollowsBtn(
       modifier: Modifier = Modifier,
       text: String,
       onClick: () -> Unit,
       isOutline: Boolean = false,
) {
    Button(
           modifier = modifier.fillMaxWidth().heightIn(30.dp),
           onClick = onClick,
           colors = if (isOutline) {
               ButtonDefaults.outlinedButtonColors()
           } else {
               ButtonDefaults.buttonColors()
           },
           border = if (isOutline) {
               ButtonDefaults.outlinedBorder
           } else {
               null
           },
           elevation = ButtonDefaults.elevation(
                  defaultElevation = 2.dp
           )
    ) {
        Text(
               text = text,
               style = MaterialTheme.typography.button.copy(
                      fontSize = 12.sp
               ),
               color = if (MaterialTheme.colors.isLight) Color.White else Gray
        )
    }

}
