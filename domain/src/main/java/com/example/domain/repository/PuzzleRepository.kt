package com.example.domain.repository

import com.example.domain.model.ImgModel
import com.example.domain.model.MatchModel
import com.example.domain.model.PuzzleModel

interface  PuzzleRepository {


    fun getTeamName():List<PuzzleModel>

    fun getTeamScore():List<MatchModel>

    fun getImg():List<ImgModel>
}