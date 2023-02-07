package uz.tune.task.presentation.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.tune.task.R
import uz.tune.task.databinding.ScreenSplashBinding
import uz.tune.task.presentation.viewmodel.SplashScreenViewModel
import uz.tune.task.presentation.viewmodel.impl.SplashScreenViewModelImpl
import uz.tune.task.util.isDeviceOnline

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    lateinit var binding: ScreenSplashBinding
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openNextScreenLiveData.observe(this@SplashScreen, openNextScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = ScreenSplashBinding.bind(view)
        if (isDeviceOnline(requireContext())) {
            viewModel.openNextScreen()
        } else {
            Toast.makeText(
                requireContext(),
                "Ilovadan foydalanish uchun tarmoqga ulaning va qaytadan kiring!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val openNextScreenObserver = Observer<Unit> {
        findNavController().navigate(SplashScreenDirections.actionSplashScreenToCardsScreen())
    }

}