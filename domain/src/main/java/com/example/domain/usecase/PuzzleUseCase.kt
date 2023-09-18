package com.example.domain.usecase

import com.example.domain.repository.PuzzleRepository

class PuzzleUseCase(private val repository: PuzzleRepository) {

    operator fun invoke(){
        repository.getTeamName()
        repository.getTeamScore()
        repository.getImg()
    }
}