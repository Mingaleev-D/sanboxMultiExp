package org.example.project.ui.pages.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.domain.model.EpisodeUI
import org.example.project.domain.repository.CharacterRepository
import org.example.project.utils.onError
import org.example.project.utils.onSuccess

class AllEpisodesPageViewModel(
       private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AllEpisodesUiState>(AllEpisodesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun refreshAllEpisodes(forceRefresh: Boolean = false) = viewModelScope.launch {
        if (forceRefresh) _uiState.update { AllEpisodesUiState.Loading }
        characterRepository.getAllEpisodes()
            .onSuccess { episodeList ->
                _uiState.update {
                    AllEpisodesUiState.Success(
                           data = episodeList.groupBy {
                               it.seasonNumber.toString()
                           }.mapKeys {
                               "Season ${it.key}"
                           }
                    )
                }
            }.onError {
                _uiState.update { AllEpisodesUiState.Error }
            }
    }
}

sealed interface AllEpisodesUiState {
    object Error : AllEpisodesUiState
    object Loading : AllEpisodesUiState
    data class Success(val data: Map<String, List<EpisodeUI>>) : AllEpisodesUiState
}
