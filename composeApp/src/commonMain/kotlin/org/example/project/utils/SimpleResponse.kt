package org.example.project.utils

import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

/**
 * Базовый класс для представления результатов выполнения запроса.
 */
sealed class SimpleResponse<out T> {

    /**
     * Представляет успешный результат с данными [data].
     */
    data class Success<out T>(val data: T) : SimpleResponse<T>()

    /**
     * Представляет ошибку с сообщением [message].
     */
    data class Error(val message: String) : SimpleResponse<Nothing>()

    /**
     * Представляет состояние загрузки.
     */
    data object Loading : SimpleResponse<Nothing>()
}

/**
 * Выполняет действие [action], если `SimpleResponse` является успешным.
 *
 * @param action Функция, которая вызывается с данными в случае успеха.
 * @return Исходный объект `SimpleResponse` для удобного чейнинга.
 */
inline fun <T> SimpleResponse<T>.onSuccess(action: (T) -> Unit = {}): SimpleResponse<T> {
    if (this is SimpleResponse.Success) action(data)
    return this
}

/**
 * Выполняет действие [action], если `SimpleResponse` содержит ошибку.
 *
 * @param action Функция, которая вызывается с сообщением ошибки.
 * @return Исходный объект `SimpleResponse` для удобного чейнинга.
 */
inline fun <T> SimpleResponse<T>.onError(action: (String) -> Unit = {}): SimpleResponse<T> {
    if (this is SimpleResponse.Error) action(message)
    return this
}

/**
 * Выполняет действие [action], если `SimpleResponse` находится в состоянии загрузки.
 *
 * @param action Функция, вызываемая в случае состояния загрузки.
 * @return Исходный объект `SimpleResponse` для удобного чейнинга.
 */
inline fun <T> SimpleResponse<T>.onLoading(action: () -> Unit = {}): SimpleResponse<T> {
    if (this is SimpleResponse.Loading) action()
    return this
}

/**
 * Универсальный класс ошибок для обработки различных исключений в KMP.
 *
 * @param message Сообщение об ошибке.
 * @param cause Исходное исключение (если есть).
 */
sealed class AppError(val message: String, val cause: Throwable? = null) {

    /** Ошибка сети, например, отсутствие соединения с интернетом. */
    data class NetworkError(val exception: Throwable) : AppError("Network error", exception)

    /** Ошибка HTTP-запроса, например, код 404 или 500. */
    data class HttpError(val statusCode: Int, val errorMessage: String) :
           AppError("HTTP $statusCode: $errorMessage")

    /** Ошибка API, например, если сервер вернул некорректные данные. */
    data class ApiError(val errorMessage: String) : AppError("API error: $errorMessage")

    /** Ошибка парсинга данных (JSON/XML). */
    data class ParsingError(val exception: Throwable) : AppError("Data parsing error", exception)

    /** Ошибка тайм-аута запроса. */
    data class TimeoutError(val exception: Throwable) : AppError("Timeout error", exception)

    /** Неизвестная ошибка, если исключение не попадает под другие категории. */
    data class UnknownError(val exception: Throwable) : AppError("Unknown error", exception)
}

/**
 * Преобразует исключение [Throwable] в объект [AppError].
 *
 * @return Соответствующий объект `AppError` на основе типа исключения.
 */
fun Throwable.toAppError(): AppError {
    return when (this) {
        is IOException -> AppError.NetworkError(this) // Ошибка сети
        is ResponseException -> AppError.HttpError(response.status.value, response.status.description) // HTTP ошибка
        is SerializationException -> AppError.ParsingError(this) // Ошибка парсинга JSON
        is TimeoutCancellationException -> AppError.TimeoutError(this) // Таймаут запроса
        else -> AppError.UnknownError(this) // Любая другая ошибка
    }
}
