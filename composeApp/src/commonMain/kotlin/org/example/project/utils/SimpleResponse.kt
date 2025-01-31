package org.example.project.utils

sealed class SimpleResponse<out T> {
    data class Success<out T>(val data: T) : SimpleResponse<T>()
    data class Error(val message: String) : SimpleResponse<Nothing>()
    data object Loading : SimpleResponse<Nothing>()
}

inline fun <T, R> SimpleResponse<T>.map(transform: (T) -> R): SimpleResponse<R> = when (this) {
    is SimpleResponse.Success -> SimpleResponse.Success(transform(data))
    is SimpleResponse.Error -> this
    is SimpleResponse.Loading -> this
}
/*
data class ApiErrorResponse(
    val errorCode:String,
    val errorMsg:String
)

sealed class Response<out T, out E> {
    data class Success<out T>(val data: T) : Response<T, Nothing>()

    sealed class Error<E> : Response<Nothing, E>() {
        /**
         * Represents server (50x) and client (40x) errors.
         */
        data class HttpError<E>(
               val code: Int,
               val errorBody: E?
        ) : Error<E>()

        /**
         * Represents IOExceptions and connectivity issues.
         */
        data object NetworkError : Error<Nothing>()

        /**
         * Represents SerializationExceptions.
         */
        data object SerializationError : Error<Nothing>()

        /**
         * Default error case with optional message.
         */
        data class DefaultError(val message: String? = null) : Error<Nothing>()
    }

    data object Loading : Response<Nothing, Nothing>()
}

/**
 * Maps the success value if the response is successful, otherwise propagates the existing response.
 */
inline fun <T, E, R> Response<T, E>.map(mapper: (T) -> R): Response<R, E> = when (this) {
    is Response.Success -> Response.Success(mapper(data))
    is Response.Error -> this
    is Response.Loading -> this
}

/**
 * Maps the error value if the response is an error, otherwise propagates the existing response.
 */
inline fun <T, E, R> Response<T, E>.mapError(mapper: (E) -> R): Response<T, R> = when (this) {
    is Response.Success -> this
    is Response.Error -> when (this) {
        is Response.Error.HttpError -> Response.Error.HttpError(code, errorBody?.let(mapper))
        is Response.Error.NetworkError -> this
        is Response.Error.SerializationError -> this
        is Response.Error.DefaultError -> this
    }

    is Response.Loading -> this
}

/**
 * Folds the response into a single value by handling all possible cases.
 */
inline fun <T, E, R> Response<T, E>.fold(
       onSuccess: (T) -> R,
       onError: (E) -> R,
       onNetworkError: () -> R,
       onSerializationError: () -> R,
       onLoading: () -> R
): R = when (this) {
    is Response.Success -> onSuccess(data)
    is Response.Error -> when (this) {
        is Response.Error.HttpError -> onError(errorBody as E)
        is Response.Error.NetworkError -> onNetworkError()
        is Response.Error.SerializationError -> onSerializationError()
        is Response.Error.DefaultError -> onError(null as E)
    }

    is Response.Loading -> onLoading()
}
/*
DefaultError поддерживает message
Позволяет передавать сообщение об ошибке:
val error = Response.Error.DefaultError("Unknown error occurred")

Позволяет трансформировать ошибки, например, из HttpError<String> в HttpError<CustomError>:
val response: Response<List<String>, String> = Response.Error.HttpError(404, "Not Found")

val mappedError = response.mapError { errorString ->
    CustomError(message = errorString ?: "Unknown error")
}

fold
Позволяет обработать все возможные состояния Response в одном месте:

val result = response.fold(
    onSuccess = { data -> "Data received: $data" },
    onError = { error -> "Error occurred: $error" },
    onNetworkError = { "Network error. Check your connection." },
    onSerializationError = { "Serialization failed." },
    onLoading = { "Loading..." }
)

 */

 */
