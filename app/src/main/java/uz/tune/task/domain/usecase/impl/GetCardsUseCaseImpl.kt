package uz.tune.task.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.tune.task.data.model.CardData
import uz.tune.task.data.repository.Repository
import uz.tune.task.domain.usecase.GetCardsUseCase
import javax.inject.Inject

class GetCardsUseCaseImpl @Inject constructor(private val repository: Repository) : GetCardsUseCase {
    override fun getCards(): Flow<List<CardData>> = repository.getList()
}