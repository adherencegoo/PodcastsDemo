package xdd.example.podcastsdemo.model.repo

import retrofit2.Call
import retrofit2.http.GET
import xdd.example.podcastsdemo.model.data.PodcastListBase

interface PodcastService {
    @GET("/getcasts")
    fun allPodcasts(): Call<PodcastListBase>
}