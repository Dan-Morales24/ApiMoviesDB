package com.example.injectiondependenceanddatapersistence.data

import com.example.injectiondependenceanddatapersistence.data.local.MoviesDao
import com.example.injectiondependenceanddatapersistence.remote.CharacterRemoteDataSource
import com.example.injectiondependenceanddatapersistence.util.performGetOperation
import javax.inject.Inject



class MovieRepository@Inject constructor(

    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: MoviesDao

    ) {

    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it)}
    )


    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )


    fun getUpComings() = performGetOperation(
        databaseQuery = {localDataSource.getAllUpComings()},
        networkCall = {remoteDataSource.getUpcomings()},
        saveCallResult = {localDataSource.insertAllUpComing(it.results)}

    )

    fun getUpComing(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getComing(id) },
        networkCall = { remoteDataSource.getUpComing(id) },
        saveCallResult = { localDataSource.insertComing(it)}
    )




        }