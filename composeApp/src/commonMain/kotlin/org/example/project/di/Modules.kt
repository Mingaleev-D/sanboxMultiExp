package org.example.project.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.project.data.local.DatabaseFactory
import org.example.project.data.local.TodosDatabase
import org.example.project.data.remote.ApiService
import org.example.project.data.remote.HttpClientFactory
import org.example.project.data.repository.TodoRepositoryImpl
import org.example.project.domain.repository.TodoRepository
import org.example.project.ui.pages.TodosViewModel
import org.example.project.urils.provideDispatcher
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformModule: Module
val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    factory { ApiService(get()) }
    factory { provideDispatcher() }

    single {
        get<DatabaseFactory>().create().setDriver(BundledSQLiteDriver()).build()
    }
    single { get<TodosDatabase>().todoDao() }

    single<TodoRepository> { TodoRepositoryImpl(get(), get(), get()) }

    viewModel {
        TodosViewModel(
               todoUseCase = get(),
               dispatcherProvider = get()
        )
    }
}
