package org.example.project.ui.pages.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.domain.model.TodoItemUI
import org.example.project.ui.components.ArchiveBtn
import org.example.project.ui.components.CompleteBtn
import org.example.project.ui.components.DeleteBtn
import org.example.project.ui.components.getTodoColors

@Composable
fun TodoItemCard(
       modifier: Modifier = Modifier,
       todo: TodoItemUI,
       onDeleteClick: () -> Unit,
       onCompleteClick: () -> Unit,
       onArchiveClick: () -> Unit,
       onCardClick: () -> Unit
) {
    val todoColors = getTodoColors(todo = todo)

    Card(
           modifier = modifier.fillMaxWidth().clickable { onCardClick() },
           shape = RoundedCornerShape(24.dp),
           colors = CardDefaults.cardColors(
                  containerColor = todoColors.backgroundColor
           )
    ) {
        Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.Start
        ) {
            CompleteBtn(
                   onCompleteClick = onCompleteClick,
                   color = todoColors.checkColor,
                   completed = todo.completes,
            )
            Text(
                   text = todo.title,
                   style = MaterialTheme.typography.headlineMedium,
                   fontWeight = FontWeight.Bold,
                   color = todoColors.textColor,
                   fontSize = 32.sp,
                   maxLines = 1,
                   overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
        }

        Row(
               verticalAlignment = androidx.compose.ui.Alignment.Top,
        ) {
            Column(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(start = 8.dp),
                   verticalArrangement = Arrangement.Top
            ) {
                Text(
                       text = todo.description,
                       style = MaterialTheme.typography.bodyLarge,
                       fontWeight = FontWeight.Normal,
                       color = todoColors.textColor,
                       fontSize = 24.sp,
                       maxLines = 10,
                       overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }
            Column(
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                   modifier = Modifier.fillMaxWidth().weight(.1f).padding(end = 4.dp),
            ) {
                ArchiveBtn(
                       onArchiveClick = onArchiveClick,
                       color = todoColors.textColor
                )
                DeleteBtn(
                       onDeleteClick = onDeleteClick
                )
            }
        }
    }

}
