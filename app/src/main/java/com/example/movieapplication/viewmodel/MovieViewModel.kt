package com.example.movieapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.model.Movie
import com.example.movieapplication.network.repository.MovieRepository
import kotlinx.coroutines.*

/**
 * This method used to initialize view model
 */
class MovieViewModel(private val movieRepository:MovieRepository): ViewModel() {

    private val errorMessage = MutableLiveData<String>()

    private val movieList = MutableLiveData<List<Movie>>()


    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler{
        _,throwable ->
        onError("Exception Handled : ${throwable.localizedMessage}")
    }

    /**
     * This method used to get moview list from server
     */
    fun getMovieList(currentPage:Int){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = movieRepository.getMovieList(currentPage)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        var list = mutableListOf<Movie>()
                        list.addAll(it.data.data)
                        movieList.postValue(list)
                    }

                }else{
                    onError("Error : ${response.message()}")
                }
            }


        }
    }

    /**
     * This method used for set value for error message in live data
     * @param message error message that need to show to user
     */
    private fun onError(message: String) {
        errorMessage.value = message
    }

    /**
     * This method used to get insatnce of error message live data
     * @return errorMessage insrance of error message live data
     */
    fun getErrorObservable() = errorMessage

    /**
     * This method used to get insatnce of movie list live data
     * @return movieList instance of movie list live data
     */
    fun getMovieListObservable() = movieList



    override fun onCleared() {
        super.onCleared()
        job!!.cancel()
    }
}