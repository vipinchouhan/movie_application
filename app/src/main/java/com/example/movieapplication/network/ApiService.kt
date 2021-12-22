package com.example.movieapplication.network

import com.example.movieapplication.utility.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
     var movieApiService : MovieApiService?= null
    fun getInstance(): MovieApiService {
        if(movieApiService == null){
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            movieApiService = retrofit.create(MovieApiService::class.java)
        }
        return movieApiService!!

    }
}