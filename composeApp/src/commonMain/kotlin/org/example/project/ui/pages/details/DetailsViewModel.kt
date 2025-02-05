package org.example.project.ui.pages.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.domain.model.CharacterItemUI
import org.example.project.domain.repository.CharacterRepository
import org.example.project.ui.components.DataPoint
import org.example.project.utils.onError
import org.example.project.utils.onSuccess

class DetailsViewModel(
       private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _internalStoreFlow =
           MutableStateFlow<CharacterDetailsState>(CharacterDetailsState.Loading)
    val internalStoreFlow: StateFlow<CharacterDetailsState> = _internalStoreFlow.asStateFlow()

    fun fetchCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            _internalStoreFlow.update { return@update CharacterDetailsState.Loading }
            characterRepository.getCharacterBuId(characterId)
                .onSuccess { character ->
                    val dataPoint = buildList {
                        add(DataPoint("Last known location", character.location.name))
                        add(DataPoint("Species", character.species))
                        add(DataPoint("Gender", character.gender.displayName))
                        character.type.takeIf { it.isNotEmpty() }?.let { type ->
                            add(DataPoint("Type", type))
                        }
                        add(DataPoint("Origin", character.origin.name))
                        add(DataPoint("Episode count", character.episodeIds.size.toString()))
                    }
                    _internalStoreFlow.value = CharacterDetailsState.Success(
                           character = character,
                           characterDataPoint = dataPoint
                    )
                }
                .onError { _internalStoreFlow.value = CharacterDetailsState.Error(it) }
        }
    }

}

sealed interface CharacterDetailsState {
    object Loading : CharacterDetailsState
    data class Success(
           val character: CharacterItemUI,
           val characterDataPoint: List<DataPoint>
    ) : CharacterDetailsState

    data class Error(val message: String) : CharacterDetailsState
}
