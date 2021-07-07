package com.github.algamza.itunestrack.view.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("goneUnless")
fun goneUnless(view: View, value: Boolean){
    view.visibility = when(value){
        true -> View.VISIBLE
        else -> View.INVISIBLE
    }
}

@BindingAdapter("loadImg")
fun loadImg(view: ImageView, url: String){
    Glide.with(view.context)
        .load(url)
        .into(view)
}