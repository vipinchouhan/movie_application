package com.example.movieapplication.network

import com.example.movieapplication.model.Movie
import com.example.movieapplication.model.MovieResponse
import com.example.movieapplication.utility.Constants
import retrofit2.Response
import retrofit2.http.*

interface MovieApiService {

    @GET("news")
    @Headers(Constants.Authorization+Constants.TOKEN)
    suspend fun getMovieList(@Query(value = "page") currentPage:Int
    ) : Response<MovieResponse>


}