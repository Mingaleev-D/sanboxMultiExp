package org.example.project.data.dto.single_character


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginDTO(
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)
