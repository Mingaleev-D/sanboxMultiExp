package org.example.project.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {

    /**
     * Создает [RoomDatabase.Builder] для iOS.
     *
     * @return Builder базы данных, настроенный для iOS.
     *
     * ## Особенности реализации:
     * - Использует путь к директории документов iOS (`NSDocumentDirectory`).
     * - Формирует полный путь к файлу базы данных.
     */
    actual fun create(): RoomDatabase.Builder<TodosDatabase> {
        val dbFile = documentDirectory() + "/${TodosDatabase.DATABASE_NAME}"
        return Room.databaseBuilder<TodosDatabase>(
               name = dbFile
        )
    }

    /**
     * Получает путь к директории документов iOS.
     *
     * @return Абсолютный путь к `NSDocumentDirectory`.
     * @throws IllegalStateException Если директория не найдена.
     *
     * ## Примечания:
     * - Испетользует `NSFileManager` для доступа к системным путям.
     * - Требует аннотации `@OptIn(ExperimentalForeignApi::class)` из-за работы с iOS API.
     */
    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
               directory = NSDocumentDirectory,
               inDomain = NSUserDomainMask,
               appropriateForURL = null,
               create = false,
               error = null //NSError ? Передаем ссылку на переменную error для получения ошибки
        )
        /**
         *  var error: NSError? = null // Переменная для хранения ошибки
         *
         *     // Обрабатываем ошибку, если она есть
         *         if (error != null) {
         *             println("Error getting document directory: ${error.localizedDescription}")
         *             // В production коде нужно более корректно обрабатывать ошибки, например, возвращать Result или Either
         *             return "" // Или выбросить исключение
         *         }
         */
        return requireNotNull(documentDirectory?.path)
    }
}
