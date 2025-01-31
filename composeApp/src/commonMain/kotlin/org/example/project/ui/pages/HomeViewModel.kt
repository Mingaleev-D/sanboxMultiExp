package org.example.project.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.domain.model.GeoLocationUI
import org.example.project.domain.repository.GeoLocationRepository
import org.example.project.utils.SimpleResponse

class HomeViewModel(
       private val geoLocationRepository: GeoLocationRepository
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        getGeoLocation()
    }

    fun getGeoLocation() = viewModelScope.launch {
        geoLocationRepository.geoLocation.collect {
            _homeState.update { state ->
                state.copy(
                       selectedLocation = it,
                       isLocationSelected = it != null,
                )
            }
        }
    }

    fun saveFavouriteLocation() = viewModelScope.launch {
        homeState.value.selectedLocation?.let {
            geoLocationRepository.upsertLocation(it)
        }
    }

    fun setSelectedLocation(geoLocation: GeoLocationUI) {
        _homeState.update {
            it.copy(
                   selectedLocation = geoLocation.copy(id = 1),
                   isLocationSelected = true,
            )
        }
    }

    fun fetchGeoLocation(query: String) {
        _homeState.value = _homeState.value.copy(
               query = query,
               isLoading = true,
               error = null
        ) // Update query, set loading, clear error
        viewModelScope.launch {
            when (val response = geoLocationRepository.fetchGeoLocation(query)) {
                is SimpleResponse.Success -> {
                    _homeState.value = _homeState.value.copy(
                           geoLocations = response.data,
                           isLoading = false
                    )
                }

                is SimpleResponse.Error -> {
                    _homeState.value = _homeState.value.copy(
                           error = response.message,
                           isLoading = false,
                           geoLocations = emptyList() // Clear results on error
                    )
                }

                is SimpleResponse.Loading -> {
                    // This case is already handled above (isLoading = true)
                }
            }
        }
    }
}

data class HomeState(
       val isLocationSelected: Boolean = false,
       val selectedLocation: GeoLocationUI? = null,
       val query: String = "",
       val isLoading: Boolean = false,
       val error: String? = null,
       val geoLocations: List<GeoLocationUI> = emptyList()
)
