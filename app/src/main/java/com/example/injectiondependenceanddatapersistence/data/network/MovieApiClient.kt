package com.example.injectiondependenceanddatapersistence.data.network

import com.example.injectiondependenceanddatapersistence.data.model.popular.GetMovieResponse
import com.example.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.example.injectiondependenceanddatapersistence.data.model.upcoming.GetUpcomingResponse
import com.example.injectiondependenceanddatapersistence.data.model.upcoming.UpcomingModel
import com.example.injectiondependenceanddatapersistence.util.Constants.Companion.API_KEY
import com.example.injectiondependenceanddatapersistence.util.Constants.Companion.Lenguaje
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {

        @GET("movie/popular")
        suspend fun getAllMovies(@Query("api_key") apiKey: String = API_KEY,@Query("language") language:String =Lenguaje): Response<GetMovieResponse>

        @GET("movie/{id}")
        suspend fun getMovie(@Path("id") id: Int, @Query("api_key") apiKey: String = API_KEY ,@Query("language") language:String =Lenguaje ): Response<MovieModel>

        @GET("movie/upcoming")
        suspend fun getAllUpComings(@Query("api_key") apiKey: String = API_KEY,@Query("language") language:String =Lenguaje): Response<GetUpcomingResponse>

        @GET("movie/{id}")
        suspend fun getMovieComings(@Path("id") id: Int,@Query("api_key") apiKey: String = API_KEY,@Query("language") language:String =Lenguaje): Response<UpcomingModel>





}