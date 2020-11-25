package xdd.example.podcastsdemo.model.data

import com.google.gson.annotations.SerializedName

data class PodcastContentFeed(

    @SerializedName("contentUrl") val contentUrl: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("publishedDate") val publishedDate: String,
    @SerializedName("title") val title: String
)