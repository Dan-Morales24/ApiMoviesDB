package com.pelis.injectiondependenceanddatapersistence.data.network

import com.pelis.injectiondependenceanddatapersistence.data.model.popular.GetMovieResponse
import com.pelis.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.pelis.injectiondependenceanddatapersistence.data.model.upcoming.GetUpcomingResponse
import com.pelis.injectiondependenceanddatapersistence.data.model.upcoming.UpcomingModel
import com.pelis.injectiondependenceanddatapersistence.util.Constants.Companion.API_KEY
import com.pelis.injectiondependenceanddatapersistence.util.Constants.Companion.Lenguaje
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {

        @GET("movie/now_playing")
        suspend fun getAllMovies(@Query("api_key") apiKey: String = API_KEY,@Query("language") language:String =Lenguaje): Response<GetMovieResponse>

        @GET("movie/{id}")
        suspend fun getMovie(@Path("id") id: Int, @Query("api_key") apiKey: String = API_KEY ,@Query("language") language:String =Lenguaje ): Response<MovieModel>

        @GET("movie/popular")
        suspend fun getAllUpComings(@Query("api_key") apiKey: String = API_KEY,@Query("language") language:String =Lenguaje): Response<GetUpcomingResponse>

        @GET("movie/{id}")
        suspend fun getMovieComings(@Path("id") id: Int,@Query("api_key") apiKey: String = API_KEY,@Query("language") language:String =Lenguaje): Response<UpcomingModel>





}