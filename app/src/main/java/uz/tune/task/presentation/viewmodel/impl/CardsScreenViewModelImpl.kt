package uz.tune.task.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.tune.task.data.model.CardData
import uz.tune.task.domain.usecase.GetCardsUseCase
import uz.tune.task.presentation.viewmodel.CardsScreenViewModel
import javax.inject.Inject

@HiltViewModel
class CardsScreenViewModelImpl @Inject constructor(private val cardsScreenUseCase: GetCardsUseCase) :
    CardsScreenViewModel, ViewModel() {
    override val openNextScreenLiveData = MutableLiveData<Unit>()
    override val getListLiveData = MutableLiveData<List<CardData>>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override fun openNextScreen() {
        openNextScreenLiveData.value = Unit
    }

    init {
        getList()
    }

    override fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            showProgressLiveData.postValue(true)
            cardsScreenUseCase.getCards().collectLatest {
                showProgressLiveData.postValue(false)
                getListLiveData.postValue(it)
            }
        }
    }


}