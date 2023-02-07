package uz.tune.task.domain.usecase.impl

import uz.tune.task.data.model.CardData
import uz.tune.task.data.repository.Repository
import uz.tune.task.domain.usecase.AddCardUseCase
import javax.inject.Inject

class AddCardUseCaseImpl @Inject constructor(private val repository: Repository): AddCardUseCase{
    override fun addCard(cardData: CardData) = repository.addCard(cardData)

}