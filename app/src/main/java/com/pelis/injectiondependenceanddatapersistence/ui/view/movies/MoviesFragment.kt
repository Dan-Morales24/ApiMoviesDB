package com.pelis.injectiondependenceanddatapersistence.ui.view.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pelis.injectiondependenceanddatapersistence.R
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.pelis.injectiondependenceanddatapersistence.adapters.upcoming.UpComingAdapters
import com.pelis.injectiondependenceanddatapersistence.databinding.MoviesFragmentBinding
import com.pelis.injectiondependenceanddatapersistence.ui.binding.BindingAdapters
import com.pelis.injectiondependenceanddatapersistence.ui.viewmodel.MoviesViewModel
import com.pelis.injectiondependenceanddatapersistence.util.Resource
import com.pelis.injectiondependenceanddatapersistence.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment : Fragment(),BindingAdapters.MovieItemListener,UpComingAdapters.MovieUpComingListener {

    private var binding: MoviesFragmentBinding by autoCleared()
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var adapter: BindingAdapters
    private lateinit var adapterUpComing:UpComingAdapters
    lateinit var mAdView : AdView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoviesFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()


        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        }



    private fun setupRecyclerView() {
        adapter = BindingAdapters(this)
        adapterUpComing = UpComingAdapters(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(
            context,LinearLayoutManager.HORIZONTAL,false
        )
        binding.upcoming.layoutManager = LinearLayoutManager(
            context,LinearLayoutManager.HORIZONTAL,false
        )

        binding.charactersRv.adapter = adapter
        binding.upcoming.adapter = adapterUpComing
    }


    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {

                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))

                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })

        viewModel.moviesUpcoming.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {

                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapterUpComing.setItems(ArrayList(it.data))

                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })


    }

    override fun onClickedCharacter(MovieId: Int) {
        findNavController().navigate(
            R.id.action_moviesFragment_to_moviesDetails,
            bundleOf("id" to MovieId)
        )


    }

    override fun onClickedUpComing(MovieId: Int) {
        findNavController().navigate(
            R.id.action_moviesFragment_to_premieresFragment,
            bundleOf("id" to MovieId)
        )
    }


}