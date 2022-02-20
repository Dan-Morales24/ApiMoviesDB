package com.example.injectiondependenceanddatapersistence.ui.view.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.example.injectiondependenceanddatapersistence.databinding.FragmentMoviesDetailsBinding
import com.example.injectiondependenceanddatapersistence.ui.viewmodel.Details.MoviesDetailsViewModel
import com.example.injectiondependenceanddatapersistence.util.Resource
import com.example.injectiondependenceanddatapersistence.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesDetails : Fragment() {
        private var binding : FragmentMoviesDetailsBinding by autoCleared()
        private val viewModel : MoviesDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
        when(it.status){
            Resource.Status.SUCCESS ->{
                bindCharacter(it.data!!)
                binding.progressBar.visibility = View.GONE
                binding.characterCl.visibility = View.VISIBLE
            }

            Resource.Status.ERROR ->
                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

            Resource.Status.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.characterCl.visibility = View.GONE
            }

        }
        })

    }

    private fun bindCharacter(data: MovieModel) {

        binding.title.text = data.title
        binding.date.text = data.release_date
        binding.rating.text = data.vote_average
        binding.popularity.text = data.popularity
        binding.overview.text = data.overview
        Glide.with(binding.root)
            .load("https://image.tmdb.org/t/p/w342${data.poster_path}")
            .transform(CircleCrop())
            .into(binding.image)
    }


}