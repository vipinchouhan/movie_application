package com.example.movieapplication.callback

import android.view.View
import com.example.movieapplication.model.Movie

interface OnItemClickListener{
    fun onItemClick(view: View, movie: Movie)
}