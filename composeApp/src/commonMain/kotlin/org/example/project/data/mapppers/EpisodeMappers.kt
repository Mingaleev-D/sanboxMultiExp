package org.example.project.data.mapppers

import org.example.project.data.dto.episode.AllEpisodesDTO
import org.example.project.data.dto.episode.EpisodeItemDTO
import org.example.project.data.dto.episode.ResultDTO
import org.example.project.domain.model.EpisodePageUI
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

fun ResultDTO.toDomainEpisodeUI(): EpisodeUI {
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

fun AllEpisodesDTO.toDomainEpisodePage(): EpisodePageUI {
    return EpisodePageUI(
           info = EpisodePageUI.Info(
                  count = info.count,
                  pages = info.pages,
                  next = info.next,
                  prev = info.prev
           ),
           episodes = results.map { it.toDomainEpisodeUI() }
    )
}
