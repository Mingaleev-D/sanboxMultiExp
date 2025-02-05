package org.example.project.domain.repository

import org.example.project.domain.model.CharacterItemUI
import org.example.project.domain.model.EpisodeUI
import org.example.project.utils.SimpleResponse

interface CharacterRepository {

    suspend fun getCharacterBuId(id: Int): SimpleResponse<CharacterItemUI>
    suspend fun getEpisodes(episodesIds: List<Int>): SimpleResponse<List<EpisodeUI>>
    suspend fun getEpisode(episodesIds: Int): SimpleResponse<EpisodeUI>
}
