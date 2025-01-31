package org.example.project.domain.model

import org.example.project.utils.FlagUrl

data class GeoLocationUI(
       val id: Int,
       val name: String,
       val countryName: String,
       val countryCode: String,
       val flagUrl: FlagUrl,
       val countryId: Int,
       val latitude: Double,
       val longitude: Double,
       val timeZone: String,
       val elevation: Double,
)
