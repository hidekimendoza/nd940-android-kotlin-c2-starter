package com.udacity.asteroidradar

import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("pictureOfDayUpdate")
fun updatePictureOfDay(imageView: ImageView, pod: PictureOfDay?) {
    pod?.let {
        Log.i("POD", "${pod}")
        if (pod.mediaType == "video") {
            Picasso.get().load(it.thumbnailUrl).error(R.drawable.asteroid_safe).into(imageView)
        } else {
            Picasso.get().load(it.url).error(R.drawable.asteroid_safe).into(imageView)
        }
        imageView.contentDescription = pod.title
    }
}

@BindingAdapter("setStatusAsteroidsProgressBar")
fun setAsteroidsProgressBar(progressBar: ProgressBar, status: Boolean) {
    if (status) {
        progressBar.visibility = ProgressBar.VISIBLE
    } else {
        progressBar.visibility = ProgressBar.GONE
    }
}

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
