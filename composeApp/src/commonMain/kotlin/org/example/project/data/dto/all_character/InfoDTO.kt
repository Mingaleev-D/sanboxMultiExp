package org.example.project.data.dto.all_character


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDTO(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("next")
    val next: String = "",
    @SerialName("pages")
    val pages: Int = 0,
)
