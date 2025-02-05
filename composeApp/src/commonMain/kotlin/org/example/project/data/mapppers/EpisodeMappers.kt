package org.example.project.data.mapppers

import org.example.project.data.dto.episode.EpisodeItemDTO
import org.example.project.domain.model.EpisodeUI

fun EpisodeItemDTO.toDomainEpisode(): EpisodeUI {
    return EpisodeUI(
           id = id,
           name = name,
           seasonNumber = episode.filter { it.isDigit() }.take(2).toInt(),
           episodeNumber = episode.filter { it.isDigit() }.takeLast(2).toInt(),
           airDate = airDate,
           characterIdsInEpisode = characters.map {
               it.substring(startIndex = it.lastIndexOf("/") + 1).toInt()
           }
    )
}
