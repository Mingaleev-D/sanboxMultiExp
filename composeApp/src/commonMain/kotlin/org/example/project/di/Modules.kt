package org.example.project.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.project.data.local.DatabaseFactory
import org.example.project.data.local.GeolocationDatabase
import org.example.project.data.remote.ApiService
import org.example.project.data.remote.HttpClientFactory
import org.example.project.data.repository.GeoLocationRepositoryImpl
import org.example.project.domain.repository.GeoLocationRepository
import org.example.project.ui.pages.HomeViewModel
import org.example.project.utils.provideExternalCoroutineScope
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module
val sharedModule = module {
    factory<ApiService> { ApiService(get()) }

    single {
        get<DatabaseFactory>().create().setDriver(BundledSQLiteDriver()).build()
    }

    single { get<GeolocationDatabase>().geolocationDao() }
    single { HttpClientFactory.create(get()) }
    single { provideExternalCoroutineScope() }.bind()

    single {
        GeoLocationRepositoryImpl(
               apiService = get(),
               geolocationDao = get(),
               externalScope = get()
        )
    }.bind<GeoLocationRepository>()

    viewModel {
        HomeViewModel(get())
    }
}
