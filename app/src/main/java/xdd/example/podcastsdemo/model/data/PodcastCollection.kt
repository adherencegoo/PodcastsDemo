package xdd.example.podcastsdemo.model.data

import com.google.gson.annotations.SerializedName

data class PodcastCollection(

    @SerializedName("artistId") val artistId: Int,
    @SerializedName("artistName") val artistName: String,
    @SerializedName("artworkUrl100") val artworkUrl100: String,
    @SerializedName("artworkUrl600") val artworkUrl600: String,
    @SerializedName("collectionId") val collectionId: Int,
    @SerializedName("collectionName") val collectionName: String,
    @SerializedName("contentFeed") val podcastContentFeed: List<PodcastContentFeed>,
    @SerializedName("country") val country: String,
    @SerializedName("genreIds") val genreIds: String,
    @SerializedName("genres") val genres: String,
    @SerializedName("releaseDate") val releaseDate: String
)