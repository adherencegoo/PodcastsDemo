package xdd.example.podcastsdemo.model.data

import com.google.gson.annotations.SerializedName

data class PodcastList(

    @SerializedName("podcast") val podcast: List<Podcast>
)