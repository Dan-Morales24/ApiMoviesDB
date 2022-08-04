package com.pelis.injectiondependenceanddatapersistence.ui.viewmodel
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pelis.injectiondependenceanddatapersistence.data.MovieRepository
import javax.inject.Inject

class MoviesViewModel @ViewModelInject constructor (

    private val repository: MovieRepository
    ) : ViewModel(){

        val movies = repository.getCharacters()
        val moviesUpcoming = repository.getUpComings()


    }


