package com.example.injectiondependenceanddatapersistence.data.model.popular


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
 data class MovieModel(

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