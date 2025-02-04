package org.example.project.urils

sealed class SimpleResponse<out T> {
    data class Success<out T>(val data: T) : SimpleResponse<T>()
    data class Error(val message: String) : SimpleResponse<Nothing>()
    data object Loading : SimpleResponse<Nothing>()
}
