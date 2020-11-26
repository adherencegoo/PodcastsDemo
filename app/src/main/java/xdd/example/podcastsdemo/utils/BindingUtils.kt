package xdd.example.podcastsdemo.utils

import android.widget.ImageView
import android.widget.SeekBar
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

@BindingAdapter("app:playPauseIcon")
fun ImageView.playPauseIcon(playing: Boolean) {
    setImageResource(if (playing) R.drawable.exo_icon_pause else R.drawable.exo_icon_play)
}

@BindingAdapter("app:progressByPercentage")
fun SeekBar.progressByPercentage(percentage: Float) {
    progress = (percentage * max).toInt()
}