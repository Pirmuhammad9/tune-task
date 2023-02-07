package uz.tune.task.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.tune.task.data.model.CardData
import uz.tune.task.presentation.screen.CardsScreen

interface GetCardsUseCase {
    fun getCards():Flow<List<CardData>>
}