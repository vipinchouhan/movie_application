package com.example.movieapplication

import android.app.Application
import com.example.movieapplication.network.ApiService
import com.example.movieapplication.network.repository.MovieRepository

class MyApplication:Application() {

    var movieRepository:MovieRepository?=null

    override fun onCreate() {
        super.onCreate()
        initWebServices()
    }

    /**
     * This method used to initialize  webservice
     */
    private fun initWebServices(){
        ApiService.getInstance()?.let {
            movieRepository = MovieRepository(it)

        }
    }
}