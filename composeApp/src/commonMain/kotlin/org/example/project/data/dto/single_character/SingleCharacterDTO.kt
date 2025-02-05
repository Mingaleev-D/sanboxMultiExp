package org.example.project.data.dto.single_character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleCharacterDTO(
       @SerialName("created")
       val created: String = "",
       @SerialName("episode")
       val episode: List<String> = listOf(),
       @SerialName("gender")
       val gender: String = "",
       @SerialName("id")
       val id: Int = 0,
       @SerialName("image")
       val image: String = "",
       @SerialName("location")
       val location: LocationDTO = LocationDTO(),
       @SerialName("name")
       val name: String = "",
       @SerialName("origin")
       val origin: OriginDTO = OriginDTO(),
       @SerialName("species")
       val species: String = "",
       @SerialName("status")
       val status: String = "",
       @SerialName("type")
       val type: String = "",
       @SerialName("url")
       val url: String = ""
)
