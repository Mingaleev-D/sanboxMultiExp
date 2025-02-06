package org.example.project.data.repository

import org.example.project.data.dto.episode.AllEpisodesDTO
import org.example.project.data.dto.episode.ResultDTO
import org.example.project.data.mapppers.toCharacterItemUI
import org.example.project.data.mapppers.toDomainCharacterPage
import org.example.project.data.mapppers.toDomainEpisode
import org.example.project.data.mapppers.toDomainEpisodePage
import org.example.project.data.remote.ApiService
import org.example.project.domain.model.AllCharacterUI
import org.example.project.domain.model.CharacterItemUI
import org.example.project.domain.model.EpisodePageUI
import org.example.project.domain.model.EpisodeUI
import org.example.project.domain.repository.CharacterRepository
import org.example.project.utils.SimpleResponse
import org.example.project.utils.onError
import org.example.project.utils.onSuccess
import org.example.project.utils.toAppError

class CharacterRepositoryImpl(
       private val apiService: ApiService
) : CharacterRepository {

    // minimum cache
    private var characterCache = mutableMapOf<Int, CharacterItemUI>()

    override suspend fun getCharacterBuId(id: Int): SimpleResponse<CharacterItemUI> {
        // Проверяем, есть ли уже в кэше
        characterCache[id]?.let { return SimpleResponse.Success(it) }
        return try {
            apiService.getCharacter(id)
                .toCharacterItemUI()
                .also { characterCache[id] = it }
                .let { SimpleResponse.Success(it) }
        } catch (ex: Exception) {
            SimpleResponse.Error(ex.toAppError().message)
        }
    }

    override suspend fun getEpisodes(episodesIds: List<Int>): SimpleResponse<List<EpisodeUI>> {
        return try {
            val episodes = if (episodesIds.size == 1) {
                // Запрашиваем одиночный эпизод
                listOf(apiService.getEpisode(episodesIds[0]))
            } else {
                // Запрашиваем список эпизодов
                apiService.getEpisodes(episodesIds)
            }

            SimpleResponse.Success(episodes.map { it.toDomainEpisode() })

        } catch (ex: Exception) {
            SimpleResponse.Error(ex.toAppError().message)
        }
    }

    override suspend fun getEpisode(episodesIds: Int): SimpleResponse<EpisodeUI> {
        return try {
            apiService.getEpisode(episodesIds)
                .toDomainEpisode()
                .let { SimpleResponse.Success(it) }
        } catch (ex: Exception) {
            SimpleResponse.Error(ex.toAppError().message)
        }
    }

    override suspend fun getCharacterByPage(pageNumber: Int): SimpleResponse<AllCharacterUI> {
        return try {
            apiService.getCharacterByPage(pageNumber = pageNumber)
                .toDomainCharacterPage()
                .let { SimpleResponse.Success(it) }
        } catch (ex: Exception) {
            SimpleResponse.Error(ex.toAppError().message)
        }
    }

    override suspend fun getEpisodesByPages(pageNumber: Int): SimpleResponse<EpisodePageUI> {
        return try {
            apiService.getEpisodesByPages(pageNumber = pageNumber)
                .toDomainEpisodePage()
                .let { SimpleResponse.Success(it) }
        } catch (ex: Exception) {
            SimpleResponse.Error(ex.toAppError().message)

        }
    }

    override suspend fun getAllEpisodes(): SimpleResponse<List<EpisodeUI>> {
        val data = mutableListOf<EpisodeUI>()
        var exception: String? = null

        getEpisodesByPages(pageNumber = 1).onSuccess { firstPage ->
            val totalPageCount = firstPage.info.pages
            data.addAll(firstPage.episodes)

            repeat(totalPageCount - 1) { index ->
                getEpisodesByPages(pageNumber = index + 2).onSuccess { nextPage ->
                    data.addAll(nextPage.episodes)
                }.onError { error ->
                    exception = error
                }

                if (exception == null) {
                    return@onSuccess
                }
            }
        }.onError {
            exception = it
        }

        return exception?.let { SimpleResponse.Error(it) } ?: SimpleResponse.Success(data)
    }

    override suspend fun getCharacterByPages(
           pageNumber: Int,
           queryParams: Map<String, String>
    ): SimpleResponse<AllCharacterUI> {
        return try {
            val response = apiService.getCharacterByPages(pageNumber, queryParams)
            SimpleResponse.Success(response.toDomainCharacterPage())
        } catch (e: Exception) {
            SimpleResponse.Error(e.toAppError().message)
        }

    }

    override suspend fun searchAllCharacterByName(searchQuery: String): SimpleResponse<List<CharacterItemUI>> {
        return try {
            val characters = apiService.searchAllCharacterByName(searchQuery)
            SimpleResponse.Success(characters.map { it.toCharacterItemUI() })
        } catch (e: Exception) {
            SimpleResponse.Error(e.toAppError().message)
        }
    }

}
