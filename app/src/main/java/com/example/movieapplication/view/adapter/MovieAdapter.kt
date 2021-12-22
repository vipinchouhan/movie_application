package com.example.movieapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.BR
import com.example.movieapplication.R
import com.example.movieapplication.callback.OnItemClickListener
import com.example.movieapplication.databinding.MovieItemBinding
import com.example.movieapplication.model.Movie

/**
 * @author Vipin Chouhan
 * This class used to show list item view
 */
class MovieAdapter(private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<MovieAdapter.MoviewViewHolder>() {

    var movieList = mutableListOf<Movie>()


    /**
     * This method used to add movie data into existing list
     */
    fun addItem(movies: List<Movie>) {
        this.movieList.addAll(movies.toMutableList())
        notifyDataSetChanged()
    }

    class MoviewViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewViewHolder {
        return MoviewViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.movie_item,parent,false))

    }

    override fun onBindViewHolder(holder: MoviewViewHolder, position: Int) {
        holder.binding.movie = movieList[position]
        holder.binding.setVariable(BR.onItemClickListener,onItemClickListener)

    }

    override fun getItemCount() = movieList.size
}