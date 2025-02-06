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

    suspend fun getCharacter(id: Int): SingleCharacterDTO {
        return client.get("character/$id").body()
    }

    suspend fun getEpisodes(episodes: List<Int>): List<EpisodeItemDTO> {
        val idsCommaSeparated = episodes.joinToString(separator = ",")
        return client.get("episode/$idsCommaSeparated").body()
    }

    suspend fun getEpisode(episode: Int): EpisodeItemDTO {
        return client.get("episode/$episode").body()
    }

    suspend fun getCharacterByPage(pageNumber: Int): AllCharacterDTO {
        return client.get("character/?page=$pageNumber").body()
    }

    suspend fun getAllEpisodesByPages(pageNumber: Int): AllEpisodesDTO {
        return client.get("episode/?page=$pageNumber").body()
    }
}
