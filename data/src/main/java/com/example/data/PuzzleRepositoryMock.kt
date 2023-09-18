package com.example.data

import com.example.domain.model.ImgModel
import com.example.domain.model.MatchModel
import com.example.domain.model.PuzzleModel
import com.example.domain.repository.PuzzleRepository

class PuzzleRepositoryMock:PuzzleRepository {



    override fun getTeamName(): List<PuzzleModel> {
        return listOf(
            PuzzleModel(
            "1", "2", "Brazil"),
            PuzzleModel(
                "2", "1", "Argentina"),
            PuzzleModel(
                "3", "3", "France"),

            )

    }

    override fun getTeamScore(): List<MatchModel> {
        return listOf(

            //Brazil
            MatchModel(
            "1", "W", "1:0", "Germany"),
            MatchModel(
                "1", "W", "3:0", "USA"),
            MatchModel(
                "1", "W", "2:0", "Russia"),
            MatchModel(
                "1", "D", "0:0", "Portugal"),
            MatchModel(
                "1", "L", "0:2", "France"),

            //Argentina
            MatchModel(
                "2", "W", "4:0", "France"),
            MatchModel(
                "2", "W", "1:0", "England"),
            MatchModel(
                "2", "W", "2:0", "Brazil"),
            MatchModel(
                "2", "D", "0:0", "Croatia"),
            MatchModel(
                "2", "L", "0:2", "Germany"),


            MatchModel(
                "3", "D", "0:0", "Argentina"),
            MatchModel(
                "3", "W", "1:0", "England"),
            MatchModel(
                "3", "W", "2:0", "Brazil"),
            MatchModel(
                "3", "D", "0:0", "Croatia"),
            MatchModel(
                "3", "L", "0:2", "Germany"),


            )
    }

    override fun getImg(): List<ImgModel> {
        return listOf(ImgModel(
            "Brazil", R.drawable.img_brazil),
            ImgModel(
                "France", R.drawable.img_france),
            ImgModel(
                "Italy", R.drawable.img_italy),
            ImgModel(
                "Germany", R.drawable.img_fermany),


            )
    }


}