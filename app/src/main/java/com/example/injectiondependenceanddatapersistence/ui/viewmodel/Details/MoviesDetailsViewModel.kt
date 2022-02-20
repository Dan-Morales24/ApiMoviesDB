package com.example.injectiondependenceanddatapersistence.ui.viewmodel.Details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.injectiondependenceanddatapersistence.data.MovieRepository
import com.example.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.example.injectiondependenceanddatapersistence.util.Resource

class MoviesDetailsViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val character: LiveData<Resource<MovieModel>> = _character


    fun start(id: Int) {
        _id.value = id
    }


}