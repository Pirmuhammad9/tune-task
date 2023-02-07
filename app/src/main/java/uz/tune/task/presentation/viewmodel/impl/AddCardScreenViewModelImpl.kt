package uz.tune.task.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.tune.task.data.model.CardData
import uz.tune.task.domain.usecase.AddCardUseCase
import uz.tune.task.domain.usecase.GetCardsUseCase
import uz.tune.task.presentation.viewmodel.AddCardScreenViewModel
import javax.inject.Inject

@HiltViewModel
class AddCardScreenViewModelImpl @Inject constructor(
    private val addCardUseCase: AddCardUseCase
) : AddCardScreenViewModel, ViewModel() {
    override val backToPreviousScreenLiveData = MutableLiveData<Unit>()
    override val addCardMessageLiveData = MutableLiveData<String>()

    override fun addData(cardData: CardData) {
        viewModelScope.launch(Dispatchers.IO) {
            addCardUseCase.addCard(cardData).collectLatest {
                if (it) {
                    addCardMessageLiveData.postValue("Muvofaqqiyatli qo'shildi!")
                } else {
                    addCardMessageLiveData.postValue("Kiritilgan ma'lumotlar to'g'riligiga ishonch hosil qiling!")
                }
            }
        }
    }

    override fun backToPreviousScreen() {
        backToPreviousScreenLiveData.value = Unit
    }

}