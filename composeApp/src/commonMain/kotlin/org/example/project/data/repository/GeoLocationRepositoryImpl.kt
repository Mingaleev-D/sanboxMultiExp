package org.example.project.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.project.data.local.GeolocationDao
import org.example.project.data.remote.ApiService
import org.example.project.domain.model.GeoLocationUI
import org.example.project.domain.repository.GeoLocationRepository
import org.example.project.utils.SimpleResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import org.example.project.data.mapper.toGeoLocationEntity
import org.example.project.data.mapper.toGeoLocationEntityListFromDTO
import org.example.project.data.mapper.toGeoLocationUI
import org.example.project.data.mapper.toGeoLocationUIList

class GeoLocationRepositoryImpl(
       private val apiService: ApiService,
       private val geolocationDao: GeolocationDao,
       private val externalScope: CoroutineScope
) : GeoLocationRepository {

    override val geoLocation: Flow<GeoLocationUI?>
        get() {
            return geolocationDao
                .getGeoLocation()
                .map { it?.toGeoLocationUI() }
                .shareIn(scope = externalScope, started = SharingStarted.Lazily)
        }

    override suspend fun upsertLocation(geoLocation: GeoLocationUI) {
        geolocationDao.upsertGeoLocation(geoLocation.toGeoLocationEntity())
    }

    override suspend fun fetchGeoLocation(query: String): SimpleResponse<List<GeoLocationUI>> {
        return try {
            val geoLocationDTO = apiService.searchLocation(query)
            val uiList = geoLocationDTO.results.toGeoLocationEntityListFromDTO().toGeoLocationUIList()
            SimpleResponse.Success(uiList)
        } catch (e: Exception) {
            SimpleResponse.Error("Network error: ${e.message}") // More specific error message
        }
    }

    override suspend fun clearGeoLocation() {
        geolocationDao.clearGeoLocation()
    }

    override suspend fun clear() {
        externalScope.cancel()
        // geolocationDao.clearGeoLocation()
    }
}
