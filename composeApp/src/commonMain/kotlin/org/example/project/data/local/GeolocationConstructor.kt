package org.example.project.data.local

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object GeolocationConstructor : RoomDatabaseConstructor<GeolocationDatabase> {

    override fun initialize(): GeolocationDatabase
}
