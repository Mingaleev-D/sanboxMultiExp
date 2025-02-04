package org.example.project.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.example.project.domain.model.TodoItemUI

data class TodoItemColors(
       val backgroundColor: Color,
       val textColor: Color,
       val archiveColor: Color,
       val checkColor: Color
)

@Composable
fun getTodoColors(
       todo: TodoItemUI
): TodoItemColors {
    return if (todo.archived) {
        TodoItemColors(
               backgroundColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
               textColor = MaterialTheme.colorScheme.onSecondary,
               archiveColor = MaterialTheme.colorScheme.onSecondary,
               checkColor = if (todo.completes) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.onSecondary
        )
    } else {
        TodoItemColors(
               backgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
               textColor = MaterialTheme.colorScheme.onPrimaryContainer,
               archiveColor = MaterialTheme.colorScheme.secondary,
               checkColor = if (todo.completes) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.secondary
        )
    }
}
