package com.example.injectiondependenceanddatapersistence.ui.binding

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.injectiondependenceanddatapersistence.data.model.popular.MovieModel
import com.example.injectiondependenceanddatapersistence.databinding.ItemMovieBinding


class BindingAdapters(private val listener: MovieItemListener): RecyclerView.Adapter<MovieViewHolder>(){

    interface MovieItemListener {
        fun onClickedCharacter(MovieId: Int)
    }

    private val items = ArrayList<MovieModel>()


    fun setItems(items: ArrayList<MovieModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])
}

class MovieViewHolder(private val itemBinding: ItemMovieBinding, private val listener: BindingAdapters.MovieItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var movie: MovieModel

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: MovieModel) {
        this.movie = item
       // itemBinding.name.text = item.title
        //itemBinding.speciesAndStatus.text = """${item.species} - ${item.status}"""
        Glide.with(itemBinding.root)
            .load("https://image.tmdb.org/t/p/w342${item.poster_path}")
            .transform(CenterCrop())
            .into(itemBinding.itemMoviePoster)
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(movie.id)
    }



}