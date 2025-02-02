package org.example.project.ui.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.core.model.fake_data.AllPostDTO
import org.example.project.core.model.fake_data.Comments
import org.example.project.core.model.fake_data.sampleComments
import org.example.project.core.model.fake_data.samplePosts

class PostDetailsViewModel : ViewModel() {

    var postUIState by mutableStateOf(PosyUIState())
        private set
    var commentUIState by mutableStateOf(CommentUIState())
        private set

//    init {
//        fetchData()
//
//    }

    fun fetchData(postId: Int) {
        viewModelScope.launch {
            postUIState = postUIState.copy(isLoading = true)
            commentUIState = commentUIState.copy(isLoading = true)

            delay(1000)

            postUIState = postUIState.copy(
                   isLoading = false,
                   post = samplePosts.find { it.id == postId }
            )
            commentUIState = commentUIState.copy(
                   isLoading = false,
                   comments = sampleComments
            )
        }

    }
}

data class PosyUIState(
       val isLoading: Boolean = false,
       val post: AllPostDTO? = null,
       val errorMsg: String? = null
)

data class CommentUIState(
       val isLoading: Boolean = false,
       val comments: List<Comments> = listOf(),
       val errorMsg: String? = null
)
