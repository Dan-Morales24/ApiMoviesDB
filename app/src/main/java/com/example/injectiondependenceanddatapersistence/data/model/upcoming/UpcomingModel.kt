package com.example.injectiondependenceanddatapersistence.data.model.upcoming

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MoviesUpComing")
data class UpcomingModel (

    @PrimaryKey
    val id:Int,
    val title: String,
    val overview: String,
    val popularity:String,
    val poster_path: String,
    val backdrop_path: String,
    val vote_average: String,
    val release_date: String

)