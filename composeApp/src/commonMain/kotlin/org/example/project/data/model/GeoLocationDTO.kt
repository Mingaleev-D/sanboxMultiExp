package org.example.project.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoLocationDTO(
    @SerialName("generationtime_ms")
    val generationtimeMs: Double = 0.0,
    @SerialName("results")
    val results: List<GetLocationItemDTO> = listOf()
)
