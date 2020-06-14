package com.bashu.kapri.appmanager.utils

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bashu.kapri.appmanager.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

object ImageHelper {
    fun showImage(context: Context, uri: Uri, imageView: ImageView) {
        try {
            if (uri != Uri.EMPTY)
                Glide.with(context).load(uri).apply(RequestOptions().error(R.drawable.android))
                    .into(imageView)
            else {
                val img = ContextCompat.getDrawable(context, R.drawable.android)
                imageView.setImageDrawable(img)
            }
        } catch (e: Exception) {
            val img = ContextCompat.getDrawable(context, R.drawable.android)
            imageView.setImageDrawable(img)
        }
    }
}