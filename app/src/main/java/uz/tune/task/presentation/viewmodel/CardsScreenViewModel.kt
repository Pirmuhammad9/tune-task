package uz.tune.task.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.tune.task.data.model.CardData

interface CardsScreenViewModel {

    val openNextScreenLiveData: LiveData<Unit>
    val getListLiveData: LiveData<List<CardData>>
    val showProgressLiveData: LiveData<Boolean>

    fun openNextScreen()
    fun getList()
}