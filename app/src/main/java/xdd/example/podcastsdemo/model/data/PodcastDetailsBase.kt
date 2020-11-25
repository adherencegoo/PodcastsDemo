package xdd.example.podcastsdemo.model.data

import com.google.gson.annotations.SerializedName

data class PodcastDetailsBase(

    @SerializedName("data") val podcastDetails: PodcastDetails
)