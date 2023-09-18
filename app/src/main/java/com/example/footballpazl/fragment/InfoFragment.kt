package com.example.footballpazl.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.data.PuzzleRepositoryMock
import com.example.domain.model.PuzzleModel
import com.example.footballpazl.R
import com.example.footballpazl.adapter.PuzzleAdapter
import com.example.footballpazl.adapter.ScoreAdapter
import com.example.footballpazl.databinding.FragmentInfoBinding
import com.example.footballpazl.viewmodel.PuzzleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var binding:FragmentInfoBinding
    private val viewModel by viewModels<PuzzleViewModel>()
    private val adapterTeamName = PuzzleAdapter(this::onClick)

    private val adapterScore = ScoreAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initBtn()
    }

    private fun initBtn() {
        binding.btnMoreInfo.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fr_container, SecondFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initAdapter() {
        binding.rvTeam.adapter = adapterTeamName
        binding.rvScore.adapter = adapterScore

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.puzzle.collect{
                adapterTeamName.submitList(it.teamName)

            }
        }
        viewModel.loadTeamName()

    }

    private fun onClick(model : PuzzleModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.puzzle.collect{
                val score = it.teamScore.filter { model.id == it.id }
                adapterScore.submitList(score)
            }
        }
        viewModel.loadTeamScore()
    }
}