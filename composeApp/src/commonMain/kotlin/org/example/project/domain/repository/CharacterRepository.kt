package org.example.project.domain.repository

import org.example.project.domain.model.AllCharacterUI
import org.example.project.domain.model.CharacterItemUI
import org.example.project.domain.model.EpisodePageUI
import org.example.project.domain.model.EpisodeUI
import org.example.project.utils.SimpleResponse

interface CharacterRepository {


    suspend fun getCharacterByPages(
           pageNumber: Int,
           queryParams: Map<String, String>
    ): SimpleResponse<AllCharacterUI>

    suspend fun searchAllCharacterByName(searchQuery: String): SimpleResponse<List<CharacterItemUI>>

    suspend fun getCharacterBuId(id: Int): SimpleResponse<CharacterItemUI>
    suspend fun getEpisodes(episodesIds: List<Int>): SimpleResponse<List<EpisodeUI>>
    suspend fun getEpisode(episodesIds: Int): SimpleResponse<EpisodeUI>
    suspend fun getCharacterByPage(pageNumber: Int): SimpleResponse<AllCharacterUI>
    suspend fun getEpisodesByPages(pageNumber: Int): SimpleResponse<EpisodePageUI>
    suspend fun getAllEpisodes(): SimpleResponse<List<EpisodeUI>>

}
