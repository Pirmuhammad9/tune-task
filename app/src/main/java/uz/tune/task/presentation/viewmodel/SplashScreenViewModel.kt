package uz.tune.task.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {
    val openNextScreenLiveData: LiveData<Unit>
    fun openNextScreen()
}