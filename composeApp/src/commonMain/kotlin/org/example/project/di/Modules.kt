package org.example.project.di

import org.example.project.core.remote.HttpClientFactory
import org.example.project.feature.auth.data.remote.AuthService
import org.example.project.feature.auth.data.repository.AuthRepositoryImpl
import org.example.project.feature.auth.domain.repository.AuthRepository
import org.example.project.feature.auth.domain.usecase.SignInUseCase
import org.example.project.feature.auth.domain.usecase.SignUpUseCase
import org.example.project.ui.auth.login.LoginViewModel
import org.example.project.ui.auth.signup.SignUpViewModel
import org.example.project.ui.home.HomeViewModel
import org.example.project.utils.provideDispatcher
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * что бы не забыть
 * single:
 *
 * Вам нужен единственный экземпляр объекта, который будет доступен во всем приложении.
 * Пример: HttpClient, Database, Repository.
 *
 * factory:
 *
 * Вам нужен новый экземпляр объекта каждый раз при запросе.
 * Пример: UseCase
 */
/*
single — один экземпляр на все приложение.
factory — новый экземпляр каждый раз.
 */
expect val platformModule: Module
val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    factory { AuthService(get()) }
    factory { provideDispatcher() }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory { SignInUseCase(get()) }
    factory { SignUpUseCase(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { HomeViewModel() }
}
