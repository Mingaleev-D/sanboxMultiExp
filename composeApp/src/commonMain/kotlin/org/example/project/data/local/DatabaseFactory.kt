package org.example.project.data.local

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    /**
     * Создает экземпляр [RoomDatabase.Builder] для базы данных.
     * Метод ожидает реализации для каждой платформы.
     *
     * @return [RoomDatabase.Builder] объект, который будет использован для создания базы данных.
     */

    fun create(): RoomDatabase.Builder<GeolocationDatabase>
}
