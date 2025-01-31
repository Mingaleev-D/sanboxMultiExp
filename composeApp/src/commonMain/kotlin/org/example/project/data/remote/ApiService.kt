package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.example.project.data.model.GeoLocationDTO
import org.example.project.utils.K

class ApiService(
       private val httpClient: HttpClient
) {

    suspend fun searchLocation(
           query: String
    ): GeoLocationDTO {
        return httpClient.get {
            url(urlString = K.GEO_CODING_BASE_URL + K.GEO_CODING_END_POINT)
            parameter("name", query)
        }.body<GeoLocationDTO>()
    }
}
