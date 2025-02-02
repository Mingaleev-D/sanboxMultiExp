package org.example.project.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.core.model.fake_data.AllPostDTO
import org.example.project.core.model.fake_data.FollowsUser
import org.example.project.core.model.fake_data.samplePosts
import org.example.project.core.model.fake_data.sampleUsers

class HomeViewModel() : ViewModel() {

    var postUIState by mutableStateOf(PostUIState())
        private set
    var onBoardingUIState by mutableStateOf(OnBoardingUIState())
        private set

    init {
        fetchData()
    }

     fun fetchData() {
        onBoardingUIState = onBoardingUIState.copy(isLoading = true)
        postUIState = postUIState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                delay(1000)

                onBoardingUIState = onBoardingUIState.copy(
                       isLoading = false,
                       users = sampleUsers,
                       shouldShowOnBoarding = true
                )
                postUIState = postUIState.copy(
                       isLoading = false,
                       postList = samplePosts
                )
            }catch (ex:Exception){
                println("Error: ${ex.message}")
            }

        }
    }
}

data class PostUIState(
       val isLoading: Boolean = false,
       val postList: List<AllPostDTO> = listOf(),
       val errorMsg: String? = null
)

data class OnBoardingUIState(
       val isLoading: Boolean = false,
       val users: List<FollowsUser> = listOf(),
       val errorMsg: String? = null,
       val shouldShowOnBoarding: Boolean = false
)
