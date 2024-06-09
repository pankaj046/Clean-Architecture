package dev.pankaj.cleanarchitecture.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?, placeHolder: Int = -1) {
    if (url.isNullOrEmpty()) return

    val glideRequest = Glide.with(this).load(url)
    if (placeHolder != -1) {
        glideRequest.placeholder(placeHolder)
    }
    glideRequest.into(this)}

fun ImageView.tint(color: Int) {
    setColorFilter(color)
}