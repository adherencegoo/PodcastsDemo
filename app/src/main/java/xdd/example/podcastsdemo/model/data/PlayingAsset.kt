package xdd.example.podcastsdemo.model.data

import java.io.Serializable

data class PlayingAsset(
    val coverUrl: String,
    val contentFeed: PodcastContentFeed
) : Serializable