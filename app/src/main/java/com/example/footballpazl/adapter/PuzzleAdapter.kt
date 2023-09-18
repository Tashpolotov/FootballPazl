package com.example.footballpazl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.PuzzleModel
import com.example.footballpazl.R
import com.example.footballpazl.databinding.ItemPuzzleBinding

class PuzzleAdapter(val onClick:(PuzzleModel)->Unit):ListAdapter<PuzzleModel, PuzzleAdapter.PuzzleViewHolder>(PuzzleDiff()) {

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION
    inner class PuzzleViewHolder(private val binding:ItemPuzzleBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: PuzzleModel) {
            binding.tvTeamName.text = model.teamName

            val isSelected = adapterPosition == selectedItemPosition
            val textColor = if (isSelected) {
                ContextCompat.getColor(binding.root.context, R.color.green)
            } else {
                ContextCompat.getColor(binding.root.context, R.color.white)
            }

            binding.tvTeamName.setTextColor(textColor)

            itemView.setOnClickListener {
                // Обновляем selectedItemPosition
                selectedItemPosition = adapterPosition

                // Уведомляем об изменении данных
                notifyDataSetChanged()

                onClick(model)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuzzleViewHolder {
        return PuzzleViewHolder(ItemPuzzleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PuzzleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PuzzleDiff:DiffUtil.ItemCallback<PuzzleModel>() {
    override fun areItemsTheSame(oldItem: PuzzleModel, newItem: PuzzleModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PuzzleModel, newItem: PuzzleModel): Boolean {
        return oldItem == newItem
    }

}
