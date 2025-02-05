package org.example.project.di

import org.example.project.data.repository.CharacterRepositoryImpl
import org.example.project.data.remote.ApiService
import org.example.project.data.remote.HttpClientFactory
import org.example.project.domain.repository.CharacterRepository
import org.example.project.ui.pages.details.DetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformModule: Module
val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    factory { ApiService(get()) }

    single<CharacterRepository> { CharacterRepositoryImpl(get()) }

    viewModel { DetailsViewModel(characterRepository = get()) }
}
