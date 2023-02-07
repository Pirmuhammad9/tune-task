package uz.tune.task.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.tune.task.presentation.viewmodel.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(): SplashScreenViewModel, ViewModel() {
    override val openNextScreenLiveData = MutableLiveData<Unit>()
    override fun openNextScreen() {
        viewModelScope.launch {
            delay(1500)
            openNextScreenLiveData.postValue(Unit)
        }
    }

}