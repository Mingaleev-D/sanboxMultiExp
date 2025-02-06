package org.example.project.data.dto.episode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDTO(
    @SerialName("air_date")
    val airDate: String = "",
    @SerialName("characters")
    val characters: List<String> = listOf(),
    @SerialName("created")
    val created: String = "",
    @SerialName("episode")
    val episode: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)
