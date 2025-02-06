package org.example.project.ui.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.domain.model.AllCharacterUI
import org.example.project.domain.model.CharacterItemUI
import org.example.project.domain.repository.CharacterRepository
import org.example.project.utils.onError
import org.example.project.utils.onSuccess

class HomeViewModel(
       private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val homeState: StateFlow<HomeUIState> = _homeState.asStateFlow()
    private val fetchCharacterPages = mutableListOf<AllCharacterUI>()

    fun fetchInitPage() {
        viewModelScope.launch {
            //todo для сохранения состояния скролла
            if (fetchCharacterPages.isNotEmpty()) return@launch
            val initPage = characterRepository.getCharacterByPage(pageNumber = 1)
            initPage.onSuccess { character ->
                fetchCharacterPages.clear()
                fetchCharacterPages.add(character)

                _homeState.update {
                    HomeUIState.GridDisplay(characters = character.characters)
                }
            }.onError {
                println("Error: $it")
            }
        }

    }

    fun getNextPage() {
        viewModelScope.launch {
            val nextPageIndex = fetchCharacterPages.size + 1
            val nextPageResult = characterRepository.getCharacterByPage(pageNumber = nextPageIndex)

            nextPageResult.onSuccess { newPage ->
                fetchCharacterPages.add(newPage) // Отслеживаем загруженные страницы
                _homeState.update { currentState ->  // Используем текущее состояние!
                    if (currentState is HomeUIState.GridDisplay) {
                        val updatedCharacters = currentState.characters + newPage.characters // Добавляем новые элементы
                        HomeUIState.GridDisplay(characters = updatedCharacters)
                    } else {
                        // Обрабатываем случай, когда состояние не GridDisplay (например, начальная загрузка)
                        // Это не должно происходить часто, но лучше подстраховаться. Возможно, стоит залогировать это.
                        currentState // Или, возможно, инициализировать данными из newPage, если это первая страница.
                    }
                }
            }.onError { error ->
                // Обрабатываем ошибку, например, _homeState.value = HomeUIState.Error(error.toString())
                println("Ошибка загрузки следующей страницы: $error") // Логируем ошибку для отладки
            }
        }
    }
}

sealed interface HomeUIState {
    object Loading : HomeUIState
    data class GridDisplay(
           val characters: List<CharacterItemUI> = emptyList(),
    ) : HomeUIState

    data class Error(val message: String) : HomeUIState
}
