package com.example.movieapplication.network.repository

import com.example.movieapplication.network.MovieApiService

class MovieRepository(private val movieApiService:MovieApiService) {

    suspend fun getMovieList(currentPage:Int) = movieApiService.getMovieList(currentPage)
}