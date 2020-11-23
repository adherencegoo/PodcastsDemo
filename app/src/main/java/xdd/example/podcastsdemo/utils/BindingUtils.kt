package xdd.example.podcastsdemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import xdd.example.podcastsdemo.R

@BindingAdapter("app:glideUrl")
fun ImageView.glideUrl(url: String?) {
    Glide
        .with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_podcast)
        .into(this)
}