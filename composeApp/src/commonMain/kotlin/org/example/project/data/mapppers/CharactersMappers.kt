package org.example.project.data.mapppers

import org.example.project.data.dto.all_character.AllCharacterDTO
import org.example.project.data.dto.single_character.SingleCharacterDTO
import org.example.project.domain.model.AllCharacterUI
import org.example.project.domain.model.CharacterGender
import org.example.project.domain.model.CharacterItemUI
import org.example.project.domain.model.CharacterStatus

fun SingleCharacterDTO.toCharacterItemUI(): CharacterItemUI {
    val characterGender = when (this.gender.lowercase()) {
        "female" -> CharacterGender.Female
        "male" -> CharacterGender.Male
        "genderless" -> CharacterGender.Genderless
        else -> CharacterGender.Unknown
    }
    val characterStatus = when (this.status.lowercase()) {
        "alive" -> CharacterStatus.Alive
        "dead" -> CharacterStatus.Dead
        else -> CharacterStatus.Unknown
    }

    return CharacterItemUI(
           created = created,
           episodeIds = episode.map { it.substring(it.lastIndexOf("/") + 1).toInt() },
           gender = characterGender,
           id = id,
           imageUrl = image,
           location = CharacterItemUI.Location(name = location.name, url = location.url),
           name = name,
           origin = CharacterItemUI.Origin(name = origin.name, url = origin.url),
           species = species,
           status = characterStatus,
           type = type
    )
}

fun AllCharacterDTO.toDomainCharacterPage(): AllCharacterUI {
    return AllCharacterUI(
           info = AllCharacterUI.Info(
                  count = info.count,
                  pages = info.pages,
                  next = info.next,
                  prev = info.next
           ),
           characters = results.map { it.toCharacterItemUI() }
    )
}
