package uz.tune.task.data.repository

import kotlinx.coroutines.flow.Flow
import uz.tune.task.data.model.CardData

interface Repository {

    fun getList():Flow<List<CardData>>

    fun addCard(cardData: CardData):Flow<Boolean>

}