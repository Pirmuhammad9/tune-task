package uz.tune.task.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.tune.task.domain.usecase.AddCardUseCase
import uz.tune.task.domain.usecase.GetCardsUseCase
import uz.tune.task.domain.usecase.impl.AddCardUseCaseImpl
import uz.tune.task.domain.usecase.impl.GetCardsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @[Binds Singleton]
    fun getCardsScreenUseCase(impl: GetCardsUseCaseImpl): GetCardsUseCase

    @[Binds Singleton]
    fun getAddCardScreenUseCase(impl: AddCardUseCaseImpl): AddCardUseCase


}