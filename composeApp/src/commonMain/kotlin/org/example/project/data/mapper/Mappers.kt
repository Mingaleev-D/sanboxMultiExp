package org.example.project.data.mapper

import org.example.project.data.local.model.GeoLocationEntity
import org.example.project.data.model.GetLocationItemDTO
import org.example.project.domain.model.GeoLocationUI
import org.example.project.utils.K

// DTO → Entity
fun GetLocationItemDTO.toGeoLocationEntity(): GeoLocationEntity {
    return GeoLocationEntity(
           id = this.id,
           name = this.name,
           countryName = this.country,
           countryCode = this.countryCode,
           countryId = this.countryId,
           latitude = this.latitude,
           longitude = this.longitude,
           timeZone = this.timezone,
           elevation = this.elevation.toDouble()
    )
}

// DTO List → Entity List
fun List<GetLocationItemDTO>.toGeoLocationEntityListFromDTO(): List<GeoLocationEntity> {
    return this.map { it.toGeoLocationEntity() }
}

// Entity → UI
fun GeoLocationEntity.toGeoLocationUI(): GeoLocationUI {
    return GeoLocationUI(
           id = this.id,
           name = this.name,
           countryName = this.countryName,
           countryCode = this.countryCode,
           flagUrl = K.flagUrl(this.countryCode), // Генерация URL флага
           countryId = this.countryId,
           latitude = this.latitude,
           longitude = this.longitude,
           timeZone = this.timeZone,
           elevation = this.elevation
    )
}

// Entity List → UI List
fun List<GeoLocationEntity>.toGeoLocationUIList(): List<GeoLocationUI> {
    return this.map { it.toGeoLocationUI() }
}

// UI → Entity (если нужно сохранять обратно)
fun GeoLocationUI.toGeoLocationEntity(): GeoLocationEntity {
    return GeoLocationEntity(
           id = this.id,
           name = this.name,
           countryName = this.countryName,
           countryCode = this.countryCode,
           countryId = this.countryId,
           latitude = this.latitude,
           longitude = this.longitude,
           timeZone = this.timeZone,
           elevation = this.elevation
    )
}

// UI List → Entity List
fun List<GeoLocationUI>.toGeoLocationEntityListFromUI(): List<GeoLocationEntity> {
    return this.map { it.toGeoLocationEntity() }
}

//val dtoList = listOf(
//       GetLocationItemDTO(
//              id = 1,
//              name = "New York",
//              country = "USA",
//              countryCode = "US",
//              countryId = 840,
//              latitude = 40.7128,
//              longitude = -74.0060,
//              timezone = "America/New_York",
//              elevation = 10
//       ),
//       GetLocationItemDTO(
//              id = 2,
//              name = "Los Angeles",
//              country = "USA",
//              countryCode = "US",
//              countryId = 840,
//              latitude = 34.0522,
//              longitude = -118.2437,
//              timezone = "America/Los_Angeles",
//              elevation = 71
//       )
//)

//// DTO List → Entity List
//val entityList = dtoList.toGeoLocationEntityList()
//
//// Entity List → UI List
//val uiList = entityList.toGeoLocationUIList()
//
//// UI List → Entity List (если нужно сохранить обратно)
//val entityListFromUI = uiList.toGeoLocationEntityList()
