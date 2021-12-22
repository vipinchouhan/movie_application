package com.example.movieapplication.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.MyApplication
import com.example.movieapplication.R
import com.example.movieapplication.callback.OnItemClickListener
import com.example.movieapplication.databinding.ActivityHomeScreenBinding
import com.example.movieapplication.factory.MovieViewModelFactory
import com.example.movieapplication.model.Movie
import com.example.movieapplication.utility.Constants
import com.example.movieapplication.utility.PaginationListener
import com.example.movieapplication.view.adapter.MovieAdapter
import com.example.movieapplication.view.adapter.ShowListAdapter
import com.example.movieapplication.viewmodel.MovieViewModel
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * @author Vipin Chouhan
 * This class used for showing movie and show list to the user
 */
class HomeScreenActivity : AppCompatActivity(),OnItemClickListener {

    var binding:ActivityHomeScreenBinding?=null
    var movieViewModel:MovieViewModel?=null
    lateinit var movieAdapter : MovieAdapter
    lateinit var showListAdapter:ShowListAdapter
    private var currentPage: Int = Constants.PAGE_START

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home_screen)
        initComponent()
    }

    /**
     * This method used to initialize all component for this activity
     */
    private fun initComponent(){
        initUIComponent()
        initViewModel()
        initObserver()
    }

    /**
     * This method used to initialize ui component
     */
    private fun initUIComponent(){
        binding?.let {
            showListAdapter = ShowListAdapter(this)
            movieAdapter = MovieAdapter(this)
            val showListLayoutManager= GridLayoutManager(this,3)
            it.showListRecyclerView.layoutManager = showListLayoutManager
            it.showListRecyclerView.adapter = showListAdapter
            it.movieList.adapter = movieAdapter
            it.movieList.setItemTransformer(ScaleTransformer.Builder()
                .build())

            /**
             * add scroll listener while user reach in bottom load more will call
             */
            it.showListRecyclerView.addOnScrollListener(object :PaginationListener(showListLayoutManager){
                override fun loadMoreItems() {
                    movieViewModel!!.getMovieList(currentPage)
                }

            })
        }


    }

    /**
     * This method used to initialize view model
     */
    private fun initViewModel(){
        movieViewModel = ViewModelProvider(this,MovieViewModelFactory((applicationContext as MyApplication).movieRepository!!)).get(MovieViewModel::class.java)
        movieViewModel!!.getMovieList(currentPage)
    }

    /**
     * This method used to initialize  observer
     */
    private fun initObserver(){

        movieViewModel?.let {
            it.getErrorObservable().observe(this, Observer {
                showToast(it);
            })

            it.getMovieListObservable().observe(this, Observer {
                if(it.size>0) {
                    currentPage++
                    movieAdapter.addItem(it)
                    showListAdapter.addItems(it)
                    showListAdapter.notifyItemInserted(it.size - 1)
                    binding!!.movieList.scrollToPosition(Constants.INITIAL_MOVIE_POSITION)
                }

            })
        }

    }

    /**
    * This method used to show error message for user acknowledgement
    * @param message acknowledgment message
    */
    private fun showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(view: View, movie: Movie) {
        val option:ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,view,"profile")
        startActivity(Intent(this, MovieDetailActivity::class.java).putExtra(Constants.MOVIE_INTENT_EXTRA,movie),option.toBundle())
    }
}