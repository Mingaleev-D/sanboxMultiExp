package org.example.project.domain.model

data class AllCharacterUI(
       val info: Info,
       val characters: List<CharacterItemUI>
) {

    data class Info(
           val count: Int,
           val pages: Int,
           val next: String?,
           val prev: String?
    )
}
