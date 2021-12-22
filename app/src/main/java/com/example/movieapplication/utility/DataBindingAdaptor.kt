package com.myuidemo.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieapplication.utility.Constants

object DataBindingAdaptor {

    @JvmStatic
    @BindingAdapter("image_url")
    fun setImageUrl(view:ImageView,sourceUrl:String) {
            Glide.with(view.context)
                .load(Constants.IMAGE_URL+sourceUrl)
                .into(view)
    }

}