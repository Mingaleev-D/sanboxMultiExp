package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.data.dto.all_character.AllCharacterDTO
import org.example.project.data.dto.episode.AllEpisodesDTO
import org.example.project.data.dto.episode.EpisodeItemDTO
import org.example.project.data.dto.single_character.SingleCharacterDTO

class ApiService(
       private val client: HttpClient
) {

    suspend fun getCharacterByPages(
           pageNumber: Int,
           queryParams: Map<String, String>
    ): AllCharacterDTO {
        return client.get("character") {
            url {
                parameters.append("page", pageNumber.toString())
                queryParams.forEach { (key, value) ->
                    parameters.append(key, value)
                }
            }
        }.body()

    }

    suspend fun searchAllCharacterByName(searchQuery: String): List<SingleCharacterDTO> {
        val characters = mutableListOf<SingleCharacterDTO>()
        var currentPage = 1
        var totalPages: Int? = null

        do {
            val response = getCharacterByPages(
                   pageNumber = currentPage,
                   queryParams = mapOf("name" to searchQuery)
            )
            characters.addAll(response.results)
            totalPages = response.info.pages
            currentPage++
        } while (currentPage <= (totalPages ?: 1)) // Запрашиваем все страницы
        return characters
    }

    suspend fun getCharacter(id: Int): SingleCharacterDTO {
        return client.get("character/$id").body()
    }

    suspend fun getCharacterByPage(pageNumber: Int): AllCharacterDTO {
        return client.get("character/?page=$pageNumber").body()
    }

    suspend fun getEpisodes(episodes: List<Int>): List<EpisodeItemDTO> {
        val idsCommaSeparated = episodes.joinToString(separator = ",")
        return client.get("episode/$idsCommaSeparated").body()
    }

    suspend fun getEpisode(episode: Int): EpisodeItemDTO {
        return client.get("episode/$episode").body()
    }

    suspend fun getEpisodesByPages(pageNumber: Int): AllEpisodesDTO {
        return client.get("episode/?page=$pageNumber").body()
    }

}
