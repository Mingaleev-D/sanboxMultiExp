package org.example.project.utils

sealed class SimpleResponse<out T> {
    data class Success<out T>(val data: T) : SimpleResponse<T>()
    data class Error(val message: String) : SimpleResponse<Nothing>()
    data object Loading : SimpleResponse<Nothing>()
}

//sealed class Result<T>(
//       val data: T? = null,
//       val message: String? = null
//) {
//
//    class Error<T>(
//           data: T? = null,
//           message: String
//    ) : Result<T>(data, message)
//
//    class Success<T>(data: T) : Result<T>(data)
//}
