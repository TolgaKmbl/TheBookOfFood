package com.tolgakumbul.thebookoffood.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tolgakumbul.thebookoffood.R

fun ImageView.downloadImg(url: String?, placeholder: CircularProgressDrawable) {

    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun placeHolderFactory(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadImgFromXml")
fun downloadImgFromXml(view: ImageView, url: String?) {
    view.downloadImg(url, placeHolderFactory(view.context))
}