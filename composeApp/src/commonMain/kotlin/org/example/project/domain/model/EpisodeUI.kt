package org.example.project.domain.model

data class EpisodeUI(
       val id: Int,
       val name: String,
       val seasonNumber: Int,
       val episodeNumber: Int,
       val airDate: String,
       val characterIdsInEpisode: List<Int>
)
