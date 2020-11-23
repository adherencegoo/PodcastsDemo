package xdd.example.podcastsdemo.model.data

import com.google.gson.annotations.SerializedName

data class PodcastListBase(

    @SerializedName("data") val podcastList: PodcastList
)