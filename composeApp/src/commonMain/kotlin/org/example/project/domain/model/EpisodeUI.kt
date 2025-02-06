package org.example.project.domain.model

data class EpisodeUI(
       val id: Int,
       val name: String,
       val seasonNumber: Int,
       val episodeNumber: Int,
       val airDate: String,
       val characterIdsInEpisode: List<Int>
)


data class EpisodePageUI(
       val info: Info,
       val episodes: List<EpisodeUI>
) {
    data class Info(
           val count: Int,
           val pages: Int,
           val next: String?,
           val prev: String?
    )
}
