package org.example.project.data.dto.episode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllEpisodesDTO(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<ResultDTO> = listOf()
){
    @Serializable
    data class InfoDto(
           val count: Int,
           val pages: Int,
           val next: String?,
           val prev: String?
    )
}
