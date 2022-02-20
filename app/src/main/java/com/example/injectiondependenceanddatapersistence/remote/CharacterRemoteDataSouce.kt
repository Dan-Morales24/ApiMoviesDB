package com.example.injectiondependenceanddatapersistence.remote

import com.example.injectiondependenceanddatapersistence.data.network.MovieApiClient
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: MovieApiClient
): BaseDataSource() {

    suspend fun getCharacters() = getResult { characterService.getAllMovies() }
    suspend fun getCharacter(id: Int) = getResult { characterService.getMovie(id) }
    suspend fun getUpcomings() = getResult { characterService.getAllUpComings() }
    suspend fun getUpComing(id:Int) = getResult { characterService.getMovieComings(id) }
}