package uz.tune.task.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.tune.task.data.model.CardData
import uz.tune.task.databinding.ItemCardBinding

class CardScreenAdapter : ListAdapter<CardData, CardScreenAdapter.ViewHolder>(CardDiffUtil) {
    object CardDiffUtil : DiffUtil.ItemCallback<CardData>() {
        override fun areItemsTheSame(oldItem: CardData, newItem: CardData): Boolean =
            oldItem.currentTime == newItem.currentTime

        override fun areContentsTheSame(oldItem: CardData, newItem: CardData): Boolean =
            oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardData) {
            try {
                binding.cardNumber.text =
                    "${card.cardNumber.substring(0, 8)}** **** ${card.cardNumber.substring(15)}"
            } catch (e: Exception) {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}