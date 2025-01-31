package org.example.project.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import org.example.project.data.local.model.GeoLocationEntity

@Database(entities = [GeoLocationEntity::class], version = 1)
@ConstructedBy(GeolocationConstructor::class)
abstract class GeolocationDatabase:RoomDatabase() {
    companion object{
        const val DB_NAME = "geo_location.db"
    }

    abstract fun geolocationDao(): GeolocationDao

}
