package xdd.example.podcastsdemo.model.data

import com.google.gson.annotations.SerializedName

data class PodcastDetails(

    @SerializedName("collection") val podcastCollection: PodcastCollection
)