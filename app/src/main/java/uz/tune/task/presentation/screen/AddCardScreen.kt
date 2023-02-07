package uz.tune.task.presentation.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.tune.task.R
import uz.tune.task.data.model.CardData
import uz.tune.task.databinding.ScreenAddCardBinding
import uz.tune.task.presentation.viewmodel.AddCardScreenViewModel
import uz.tune.task.presentation.viewmodel.impl.AddCardScreenViewModelImpl


@AndroidEntryPoint
class AddCardScreen : Fragment(R.layout.screen_add_card), TextWatcher {
    lateinit var binding: ScreenAddCardBinding
    private val viewModel: AddCardScreenViewModel by viewModels<AddCardScreenViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backToPreviousScreenLiveData.observe(
            this@AddCardScreen,
            backToPreviousScreenObserver
        )
        viewModel.addCardMessageLiveData.observe(
            this@AddCardScreen,
            addCardMessageObserver
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = ScreenAddCardBinding.bind(view)
        binding.cardNumber.addTextChangedListener(this)
        binding.dateInput.addTextChangedListener(this)
        binding.backButton.setOnClickListener {
            viewModel.backToPreviousScreen()
        }
        binding.continueButton.setOnClickListener {
            viewModel.addData(
                CardData(
                    date = binding.dateInput.text.toString(),
                    cardNumber = binding.cardNumber.text.toString(),
                    currentTime = System.currentTimeMillis()
                )
            )
            it.isEnabled = false
        }
    }

    private val backToPreviousScreenObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
    private val addCardMessageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
            .show()
        if (it.startsWith("Muv")) {
            binding.cardNumber.text?.clear()
            binding.dateInput.text?.clear()
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = check()
    override fun afterTextChanged(p0: Editable?) {}

    private fun check() {
        var bool =
            binding.cardNumber.text?.length == 19 && binding.dateInput.text?.length == 5
        if (bool) checkSecond()
        else {
            binding.errorText.isVisible = false
        }
    }

    private fun checkSecond() {
        val split = binding.dateInput.text.toString().split("/")
        try {
            binding.continueButton.isEnabled = split[0].toInt() < 13
            binding.errorText.isVisible = split[0].toInt() >= 13
        } catch (e: Exception) {
            binding.continueButton.isEnabled = false
        }
    }
}