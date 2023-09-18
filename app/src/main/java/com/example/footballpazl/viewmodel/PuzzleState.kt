package com.example.footballpazl.viewmodel

import com.example.domain.model.ImgModel
import com.example.domain.model.MatchModel
import com.example.domain.model.PuzzleModel

data class PuzzleState(

    val teamName:List<PuzzleModel> = emptyList(),
    val teamScore:List<MatchModel> = emptyList(),
    val img : List<ImgModel> = emptyList(),
)