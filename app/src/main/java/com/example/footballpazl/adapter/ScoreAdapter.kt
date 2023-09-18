package com.example.footballpazl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.MatchModel
import com.example.domain.model.PuzzleModel
import com.example.footballpazl.databinding.ItemPuzzleBinding
import com.example.footballpazl.databinding.ItemScoreBinding

class ScoreAdapter: ListAdapter<MatchModel, ScoreAdapter.ScoreViewHolder>(ScoreDiff()) {


    inner class ScoreViewHolder(private val binding: ItemScoreBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MatchModel) {
            binding.tvTeamName.text = model.nameTeam
            binding.tvScore.text = model.score
            binding.tvWinLose.text = model.winOrLose

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        return ScoreViewHolder(ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ScoreDiff: DiffUtil.ItemCallback<MatchModel>() {
    override fun areItemsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean {
        return oldItem == newItem
    }

}
