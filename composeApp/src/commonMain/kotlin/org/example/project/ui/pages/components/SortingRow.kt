package org.example.project.ui.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingRow(
       modifier: Modifier = Modifier,
       text: String,
       isChecked: Boolean,
) {
    Row(
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically,
           modifier = modifier.fillMaxWidth()
    ) {
        Text(
               text = text,
               fontSize = 24.sp,
               lineHeight = 30.sp
        )
        if (isChecked) {
            Icon(
                   imageVector = Icons.Default.Check,
                   contentDescription = null,
                   tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }

}
