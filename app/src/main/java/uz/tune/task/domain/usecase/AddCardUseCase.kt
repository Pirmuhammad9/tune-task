package uz.tune.task.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.tune.task.data.model.CardData

interface AddCardUseCase {
    fun addCard(cardData: CardData): Flow<Boolean>
}