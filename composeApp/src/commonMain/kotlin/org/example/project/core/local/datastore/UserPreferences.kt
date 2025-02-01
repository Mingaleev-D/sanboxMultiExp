package org.example.project.core.local.datastore

const val PREFERENCES_FILE_NAME = "app_user_settings.preferences_pb"

interface UserPreferences {

    suspend fun getUserData(): UserDataSettings
    suspend fun setUserData(userSettings: UserDataSettings)
}
