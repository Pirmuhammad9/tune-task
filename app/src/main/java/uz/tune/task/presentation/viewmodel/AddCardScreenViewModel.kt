package uz.tune.task.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.tune.task.data.model.CardData

interface AddCardScreenViewModel {
    val backToPreviousScreenLiveData: LiveData<Unit>
    val addCardMessageLiveData: LiveData<String>
    fun addData(cardData: CardData)
    fun backToPreviousScreen()
}