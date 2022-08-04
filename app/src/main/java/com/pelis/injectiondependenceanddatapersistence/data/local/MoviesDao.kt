package com.pelis.injectiondependenceanddatapersistence.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pelis.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.pelis.injectiondependenceanddatapersistence.data.model.upcoming.UpcomingModel

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movies")
    fun getAllCharacters() : LiveData<List<MovieModel>>

    @Query("SELECT * FROM Movies WHERE id = :id")
    fun getCharacter(id: Int): LiveData<MovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieModel)

    ///////////////getUpComing/////////////////////////

    @Query("SELECT * FROM MoviesUpComing")
    fun getAllUpComings() : LiveData<List<UpcomingModel>>

    @Query("SELECT * FROM MoviesUpComing WHERE id = :id")
    fun getComing(id: Int) : LiveData<UpcomingModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpComing(movies: List<UpcomingModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComing(movie: UpcomingModel)



}