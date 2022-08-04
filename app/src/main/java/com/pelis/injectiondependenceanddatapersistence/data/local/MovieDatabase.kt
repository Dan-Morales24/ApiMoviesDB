package com.pelis.injectiondependenceanddatapersistence.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pelis.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.pelis.injectiondependenceanddatapersistence.data.model.upcoming.UpcomingModel


@Database( entities = [MovieModel::class,UpcomingModel::class], version = 2, exportSchema = false)

    abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieAppDao(): MoviesDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, MovieDatabase::class.java, "Movies")
                .fallbackToDestructiveMigration()
                .build()

     }
    }



