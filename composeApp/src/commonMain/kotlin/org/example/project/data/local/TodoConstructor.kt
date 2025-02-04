package org.example.project.data.local

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object TodoConstructor : RoomDatabaseConstructor<TodosDatabase> {

    override fun initialize(): TodosDatabase
}
