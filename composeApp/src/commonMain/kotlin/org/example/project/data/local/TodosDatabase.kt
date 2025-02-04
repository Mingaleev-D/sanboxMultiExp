package org.example.project.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoItemEntity::class], version = 1)
@ConstructedBy(TodoConstructor::class)
abstract class TodosDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "todos.db"
    }

    abstract fun todoDao(): ToDoDao
}
