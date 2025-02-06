package org.example.project.data.dto.episode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllEpisodesDTO(
    @SerialName("info")
    val info: InfoDTO = InfoDTO(),
    @SerialName("results")
    val results: List<ResultDTO> = listOf()
)
