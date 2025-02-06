package org.example.project.data.dto.all_character


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.data.dto.single_character.SingleCharacterDTO

@Serializable
data class AllCharacterDTO(
    @SerialName("info")
    val info: InfoDTO = InfoDTO(),
    @SerialName("results")
    val results: List<SingleCharacterDTO> = listOf()
)
