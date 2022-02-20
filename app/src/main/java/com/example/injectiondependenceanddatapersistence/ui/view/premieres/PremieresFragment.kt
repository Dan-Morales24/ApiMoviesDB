package com.example.injectiondependenceanddatapersistence.ui.view.premieres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.injectiondependenceanddatapersistence.R
import com.example.injectiondependenceanddatapersistence.data.model.upcoming.UpcomingModel
import com.example.injectiondependenceanddatapersistence.databinding.FragmentMoviesDetailsBinding
import com.example.injectiondependenceanddatapersistence.databinding.FragmentPremieresBinding
import com.example.injectiondependenceanddatapersistence.ui.viewmodel.Details.MoviesDetailsViewModel
import com.example.injectiondependenceanddatapersistence.ui.viewmodel.premieres.PremieresViewModel
import com.example.injectiondependenceanddatapersistence.util.Resource
import com.example.injectiondependenceanddatapersistence.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PremieresFragment : Fragment() {

    private var binding : FragmentPremieresBinding by autoCleared()
    private val viewModel : PremieresViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
      binding = FragmentPremieresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.premieres.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }

            }
        })
    }


    private fun bindCharacter(data: UpcomingModel) {
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
