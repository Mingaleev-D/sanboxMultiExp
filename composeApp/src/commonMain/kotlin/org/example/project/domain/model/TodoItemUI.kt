package org.example.project.domain.model

import kotlinx.serialization.SerialName

data class TodoItemUI(
       val archived: Boolean,
       val completes: Boolean,
       val description: String,
       val id: Int,
       val timestamp: Int,
       val title: String
)
