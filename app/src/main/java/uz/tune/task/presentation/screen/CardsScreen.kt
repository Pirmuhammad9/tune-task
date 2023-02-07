package uz.tune.task.presentation.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.tune.task.R
import uz.tune.task.data.model.CardData
import uz.tune.task.databinding.ScreenCardsBinding
import uz.tune.task.presentation.screen.adapter.CardScreenAdapter
import uz.tune.task.presentation.viewmodel.CardsScreenViewModel
import uz.tune.task.presentation.viewmodel.impl.CardsScreenViewModelImpl

@AndroidEntryPoint
class CardsScreen : Fragment(R.layout.screen_cards) {
    private val adapter = CardScreenAdapter()
    lateinit var binding: ScreenCardsBinding
    private val viewModel: CardsScreenViewModel by viewModels<CardsScreenViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openNextScreenLiveData.observe(this@CardsScreen, openNextScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = ScreenCardsBinding.bind(view)
        viewModel.getList()
        setUpTabBar()
        viewModel.getListLiveData.observe(viewLifecycleOwner, getListObserver)
        viewModel.showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
        binding.recyclerView.adapter = adapter
        binding.addButton.setOnClickListener { viewModel.openNextScreen() }
        binding.backButton.setOnClickListener { this.activity?.finishAffinity() }
        binding.recycleButton.setOnClickListener {
            viewModel.getList()
        }
    }

    private val openNextScreenObserver = Observer<Unit> {
        findNavController().navigate(CardsScreenDirections.actionCardsScreenToAddCardScreen())
    }
    private val getListObserver = Observer<List<CardData>> {
        binding.message.isVisible = it.isEmpty()
        adapter.submitList(it)
    }
    private val showProgressObserver = Observer<Boolean> {
        binding.progress.isVisible = it
        binding.message.isVisible = (adapter.currentList.isEmpty() && !it)
    }

    private fun setUpTabBar() {
        val list = listOf(
            "Hammasi", "Sevimli", "UzCard", "HUMO", "Xalqaro kartalar"
        )
        list.forEach { item ->
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(item))
        }
    }
}