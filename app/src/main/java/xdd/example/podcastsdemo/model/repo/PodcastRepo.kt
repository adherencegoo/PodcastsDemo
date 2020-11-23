package xdd.example.podcastsdemo.model.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PodcastRepo {
    private const val serverUrl = "https://demo4491005.mockable.io"

    val service: PodcastService = Retrofit.Builder()
        .baseUrl(serverUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PodcastService::class.java)
}