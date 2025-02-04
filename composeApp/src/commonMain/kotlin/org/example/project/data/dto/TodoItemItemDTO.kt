package org.example.project.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoItemItemDTO(
    @SerialName("archived")
    val archived: Boolean = false,
    @SerialName("completes")
    val completes: Boolean = false,
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("timestamp")
    val timestamp: Int = 0,
    @SerialName("title")
    val title: String = ""
)
