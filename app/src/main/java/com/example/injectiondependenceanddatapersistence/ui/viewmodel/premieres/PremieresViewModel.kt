package com.example.injectiondependenceanddatapersistence.ui.viewmodel.premieres

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.injectiondependenceanddatapersistence.data.MovieRepository
import com.example.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.example.injectiondependenceanddatapersistence.data.model.upcoming.UpcomingModel
import com.example.injectiondependenceanddatapersistence.util.Resource

class PremieresViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
) : ViewModel() {

        private val _id = MutableLiveData<Int>()

        private val _premieres = _id.switchMap { id ->
            repository.getUpComing(id)
        }

            val premieres: LiveData<Resource<UpcomingModel>> = _premieres

    fun start(id: Int) {
        _id.value = id
    }

    }