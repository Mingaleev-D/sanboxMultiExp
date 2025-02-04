package org.example.project.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *      * Создает [RoomDatabase.Builder] для Android.
 *      *
 *      * @return Builder базы данных, настроенный для Android.
 *      *
 *      * ## Особенности реализации:
 *      * - Использует `applicationContext` для избежания утечек памяти.
 *      * - Формирует путь к файлу БД через `getDatabasePath()`.
 *      */
actual class DatabaseFactory(
       private val context: Context
) {

    /**
     * Создает и возвращает объект [RoomDatabase.Builder] для базы данных [GeolocationDatabase] на Android.
     *
     * @param context Контекст приложения для создания базы данных.
     * @return [RoomDatabase.Builder] объект, настроенный для использования на Android.
     *
     */
    actual fun create(): RoomDatabase.Builder<TodosDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(TodosDatabase.DATABASE_NAME)
        // Возвращаем настроенный объект Builder для Room
        return Room.databaseBuilder(
               context = appContext,
               name = dbFile.absolutePath
        )
    }
}
