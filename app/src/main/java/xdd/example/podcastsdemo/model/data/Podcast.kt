package xdd.example.podcastsdemo.model.data

import com.google.gson.annotations.SerializedName

data class Podcast(

    @SerializedName("artistName") val artistName: String,
    @SerializedName("artworkUrl100") val artworkUrl100: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)