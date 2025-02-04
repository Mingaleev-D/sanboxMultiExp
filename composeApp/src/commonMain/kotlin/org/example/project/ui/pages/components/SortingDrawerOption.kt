package org.example.project.ui.pages.components

import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import org.example.project.ui.pages.TodoItemOrder

@Composable
fun SortingDrawerOption(
       todoItemOrder: TodoItemOrder,
       onOrderChange: (TodoItemOrder) -> Unit,
) {
    val titleSelected = todoItemOrder::class == TodoItemOrder.Title::class

    NavigationDrawerItem(
           label = {
               IconRow(
                      title = "Title",)
           },
           selected = false,
           onClick = {
               onOrderChange(
                      TodoItemOrder.Title(
                             todoItemOrder.sortingDirection,
                             todoItemOrder.showArchived
                      )
               )
           }
    )

}
