package com.example.movieapplication.view.activity

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.movieapplication.R
import com.example.movieapplication.databinding.ActivityMovieDetailBinding
import com.example.movieapplication.generated.callback.OnClickListener
import com.example.movieapplication.model.Movie
import com.example.movieapplication.utility.Constants

class MovieDetailActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detail)
        if(intent.hasExtra(Constants.MOVIE_INTENT_EXTRA)){
            intent.getParcelableExtra<Movie>(Constants.MOVIE_INTENT_EXTRA).also { binding.movie = it }
        }
        binding.toolbar.setNavigationOnClickListener(this)
        binding.movieInfoLayout.animation = AnimationUtils.loadAnimation(this,R.anim.right_to_left)
    }

    override fun onClick(view: View?) {
        supportFinishAfterTransition()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }
}