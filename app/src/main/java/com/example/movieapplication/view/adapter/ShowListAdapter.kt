package com.example.movieapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.BR
import com.example.movieapplication.R
import com.example.movieapplication.callback.OnItemClickListener
import com.example.movieapplication.databinding.ShowItemBinding
import com.example.movieapplication.model.Movie


/**
 * @author Vipin Chouhan
 * This class used to show list item view
 */
class ShowListAdapter(private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<ShowListAdapter.MovieViewHolder>() {

    var showList = mutableListOf<Movie>()

    /**
     * This method used to add movie data into existing list
     * @param shows list of show
     */
    fun addItems(shows: List<Movie>) {
        this.showList.addAll(shows.toMutableList())
    }

    class MovieViewHolder(val binding: ShowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ), R.layout.show_item, parent, false
            )
        )
    }



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(holder is MovieViewHolder) {
            holder.binding.setVariable(BR.movie,showList[position])
            holder.binding.setVariable(BR.onItemClickListener,onItemClickListener)
        }
    }

    override fun getItemCount() = showList.size

}