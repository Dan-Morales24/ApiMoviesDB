package com.example.injectiondependenceanddatapersistence.di

import android.content.Context
import com.example.injectiondependenceanddatapersistence.data.MovieRepository
import com.example.injectiondependenceanddatapersistence.data.local.MovieDatabase
import com.example.injectiondependenceanddatapersistence.data.local.MoviesDao
import com.example.injectiondependenceanddatapersistence.data.network.MovieApiClient
import com.example.injectiondependenceanddatapersistence.remote.CharacterRemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): MovieApiClient = retrofit.create(MovieApiClient::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: MovieApiClient) = CharacterRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = MovieDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: MovieDatabase) = db.getMovieAppDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
                          localDataSource: MoviesDao
    ) =
        MovieRepository(remoteDataSource, localDataSource)
}