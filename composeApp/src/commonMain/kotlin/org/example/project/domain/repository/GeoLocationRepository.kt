package org.example.project.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.project.domain.model.GeoLocationUI
import org.example.project.utils.SimpleResponse

interface GeoLocationRepository {

    val geoLocation: Flow<GeoLocationUI?>
    suspend fun upsertLocation(geoLocation: GeoLocationUI)
    suspend fun fetchGeoLocation(query: String): SimpleResponse<List<GeoLocationUI>>
    suspend fun clearGeoLocation()
    suspend fun clear()
}
