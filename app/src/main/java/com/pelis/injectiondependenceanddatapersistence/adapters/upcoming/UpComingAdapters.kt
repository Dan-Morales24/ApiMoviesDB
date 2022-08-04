package com.pelis.injectiondependenceanddatapersistence.adapters.upcoming

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.pelis.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.pelis.injectiondependenceanddatapersistence.data.model.upcoming.UpcomingModel
import com.pelis.injectiondependenceanddatapersistence.databinding.ItemMovieBinding

class UpComingAdapters(private val listener:MovieUpComingListener) : RecyclerView.Adapter<MovieUpComingViewHolder>() {

    interface MovieUpComingListener {
        fun onClickedUpComing(MovieId: Int)
    }

    private val items = ArrayList<UpcomingModel>()


    fun setItems(items: ArrayList<UpcomingModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieUpComingViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieUpComingViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieUpComingViewHolder, position: Int) = holder.bind(items[position])
}

class MovieUpComingViewHolder(private val itemBinding: ItemMovieBinding, private val listener: UpComingAdapters.MovieUpComingListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var movieUpComing: UpcomingModel

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: UpcomingModel) {
        this.movieUpComing = item
        // itemBinding.name.text = item.title
        //itemBinding.speciesAndStatus.text = """${item.species} - ${item.status}"""
        Glide.with(itemBinding.root)
            .load("https://image.tmdb.org/t/p/w342${item.poster_path}")
            .transform(CenterCrop())
            .into(itemBinding.itemMoviePoster)
    }

    override fun onClick(v: View?) {
        listener.onClickedUpComing(movieUpComing.id)
    }


}