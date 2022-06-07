package com.juliablack.booksapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.juliablack.booksapp.R

fun ImageView.displayImage(context: Context, url: String) {
    val requestOptions = RequestOptions()
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)

    val requestBuilder = Glide.with(context)
        .asDrawable()
        .sizeMultiplier(0.1f)

    Glide.with(context)
        .applyDefaultRequestOptions(requestOptions)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .thumbnail(requestBuilder)
        .into(this)
}