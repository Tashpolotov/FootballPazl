package com.example.footballpazl.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.repository.PuzzleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PuzzleViewModel @Inject constructor(val repository: PuzzleRepository):ViewModel() {

    private val _puzzle = MutableStateFlow(PuzzleState())
    val puzzle : StateFlow<PuzzleState> = _puzzle.asStateFlow()


    fun loadImg(){
        val img = repository.getImg()
        _puzzle.value = _puzzle.value.copy(img = img)
    }

    fun loadTeamName(){
        val teamName = repository.getTeamName()
        _puzzle.value = _puzzle.value.copy(teamName = teamName)
    }

    fun loadTeamScore(){
        val teamScore = repository.getTeamScore()
        _puzzle.value = _puzzle.value.copy(teamScore = teamScore)
    }

}