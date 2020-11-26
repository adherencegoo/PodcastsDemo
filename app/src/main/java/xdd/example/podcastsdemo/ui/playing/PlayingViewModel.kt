package xdd.example.podcastsdemo.ui.playing

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import xdd.example.podcastsdemo.model.data.PlayingAsset
import java.util.*

class PlayingViewModel : ViewModel() {
    private val timeFormatter = SimpleDateFormat("mm:ss", Locale.getDefault())
    private val calendar = Calendar.getInstance()

    private var exoPlayer: SimpleExoPlayer? = null

    val liveLoading = MutableLiveData<Boolean>(true)
    val livePlaying = MutableLiveData<Boolean>(false)
    val livePercentage = MutableLiveData(0f)
    val liveCurrentTimeText = MutableLiveData<String>("00:00")
    val liveTotalTimeText = MutableLiveData<String>("00:00")

    var playingAsset: PlayingAsset? = null
        private set

    private var playingWhenSeekStart = false
    private var updateProgressJob: Job? = null

    private fun getExoPlayer(context: Context): ExoPlayer = exoPlayer
        ?: SimpleExoPlayer.Builder(context).build().also { exoPlayer = it }

    fun setPlayingAsset(context: Context, asset: PlayingAsset) {
        playingAsset = asset

        getExoPlayer(context).apply {
            setMediaItem(MediaItem.fromUri(playingAsset!!.contentFeed.contentUrl))

            addListener(object : Player.EventListener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    livePlaying.value = isPlaying

                    if (isPlaying) {
                        updateProgressJob = viewModelScope.launch {
                            while (isActive) {
                                delay(1000)
                                updateProgress(context)
                            }
                        }
                    } else {
                        updateProgressJob?.cancel()
                        updateProgress(context)
                    }
                }

                override fun onPlaybackStateChanged(state: Int) {
                    liveLoading.value = state == Player.STATE_BUFFERING
                    if (state == Player.STATE_READY) {
                        liveTotalTimeText.value = duration.msToTimeString()
                    }
                }
            })
            prepare()
            playWhenReady = true
        }
    }


    fun togglePlaying(view: View) {
        val newPlaying = livePlaying.value != true
        getExoPlayer(view.context).playWhenReady = newPlaying
    }

    private fun updateProgress(context: Context) {
        getExoPlayer(context).apply {
            val currentTime = currentPosition
            liveCurrentTimeText.value = currentTime.msToTimeString()
            livePercentage.value = currentTime.toFloat() / duration
        }
    }

    fun onSeekStart(seekBar: SeekBar) {
        getExoPlayer(seekBar.context).apply {
            playingWhenSeekStart = isPlaying
            pause()
        }
    }

    fun onSeekEnd(seekBar: SeekBar) {
        getExoPlayer(seekBar.context).apply {
            seekTo(duration * seekBar.progress / seekBar.max)
            playWhenReady = playingWhenSeekStart
        }
    }

    fun onSeekChanged(seekBar: SeekBar, newValue: Int, fromUser: Boolean) {
        if (fromUser) {
            val player = getExoPlayer(seekBar.context)
            liveCurrentTimeText.value = (newValue.toFloat() / seekBar.max * player.duration)
                .toLong().msToTimeString()
        }
    }

    fun onJumpClicked(view: View, distanceMs: Long) {
        getExoPlayer(view.context).apply {
            if (duration > 0) {
                seekTo((currentPosition + distanceMs).coerceIn(0, duration))
            }
        }
    }

    private fun Long.msToTimeString(): String {
        calendar.timeInMillis = this
        return timeFormatter.format(calendar.time)
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer?.pause()
    }
}