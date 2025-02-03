package org.example.project.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.core.model.fake_data.AllPostDTO
import org.example.project.core.model.fake_data.Profile
import org.example.project.core.model.fake_data.samplePosts
import org.example.project.core.model.fake_data.sampleProfile

class ProfileViewModel : ViewModel() {

    var userUIState by mutableStateOf(USerUIState())
        private set
    var profileUiState by mutableStateOf(ProfilePostUIState())
        private set

    init {

    }

    fun fetchProfile(userId: Int) {
        viewModelScope.launch {
            delay(1000)
            userUIState = userUIState.copy(
                   isLoading = false,
                   profile = sampleProfile.find { it.id == userId },
            )
            profileUiState = profileUiState.copy(
                   isLoading = false,
                   post = samplePosts
            )
        }
    }
}

data class USerUIState(
       val isLoading: Boolean = true,
       val profile: Profile? = null,
       var errorMsg: String? = null
)

data class ProfilePostUIState(
       val isLoading: Boolean = true,
       val post: List<AllPostDTO> = emptyList(),
       var errorMsg: String? = null
)
