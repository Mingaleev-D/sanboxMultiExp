package org.example.project.domain.model

import androidx.compose.ui.graphics.Color

data class CharacterItemUI(
       val created: String,
       val episodeIds: List<Int>,
       val gender: CharacterGender,
       val id: Int,
       val imageUrl: String,
       val location: Location,
       val name: String,
       val origin: Origin,
       val species: String,
       val status: CharacterStatus,
       val type: String
) {
    data class Location(
           val name: String,
           val url: String
    )

    data class Origin(
           val name: String,
           val url: String
    )
}

sealed class CharacterStatus(
       val displayName: String,
       val color: Color
) {

    object Alive : CharacterStatus("Alive", Color.Green)
    object Dead : CharacterStatus("Dead", Color.Red)
    object Unknown : CharacterStatus("Unknown", Color.Yellow)

//    companion object {
//
//        fun fromString(status: String): CharacterStatus {
//            return when (status.lowercase()) {
//                "alive" -> Alive
//                "dead" -> Dead
//                else -> Unknown // Default to Unknown for any other value
//            }
//        }
//    }
}

sealed class CharacterGender(
       val displayName: String
) {

    object Male : CharacterGender("Male")
    object Female : CharacterGender("Female")
    object Genderless : CharacterGender("No gender")
    object Unknown : CharacterGender("Unknown")

//    companion object {
//
//        fun fromString(gender: String): CharacterGender {
//            return when (gender.lowercase()) {
//                "male" -> Male
//                "female" -> Female
//                "genderless" -> Genderless
//                else -> Unknown // Default to Unknown
//            }
//        }
//    }
}
