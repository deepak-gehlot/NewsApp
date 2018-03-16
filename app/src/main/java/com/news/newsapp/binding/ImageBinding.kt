package com.news.newsapp.binding

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.androidquery.AQuery
import com.news.newsapp.R

object ImageBinding {

    @BindingAdapter("profileImageUrl")
    fun setImage(imageView: ImageView, url: String) {
        val aQuery = AQuery(imageView.context)
        aQuery.id(imageView).image(url, true, true, 80, R.drawable.no_image_available)
    }

    @BindingAdapter("postImageUrl")
    fun setPostImage(imageView: ImageView, url: String) {
        val aQuery = AQuery(imageView.context)
        aQuery.id(imageView).image(url, true, true, 300, R.drawable.no_image_available)
    }

    @BindingAdapter("imageDrawable")
    fun setImageFromDrawable(imageView: ImageView, drawable: Int) {
        val aQuery = AQuery(imageView.context)
        aQuery.id(imageView).image(drawable)
    }

}
