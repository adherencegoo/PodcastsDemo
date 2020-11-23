package xdd.example.podcastsdemo.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xdd.example.podcastsdemo.model.data.Podcast
import xdd.example.podcastsdemo.model.data.PodcastListBase
import xdd.example.podcastsdemo.model.repo.PodcastRepo

class OverviewViewModel : ViewModel() {

    val liveErrorMsg = MutableLiveData<String>()
    val liveOverviewList = MutableLiveData<List<Podcast>>()

    fun updateOverviews() {
        PodcastRepo.service.allPodcasts().enqueue(object : Callback<PodcastListBase> {
            override fun onFailure(call: Call<PodcastListBase>, t: Throwable) {
                liveOverviewList.value = emptyList()

                liveErrorMsg.value = t.localizedMessage
            }

            override fun onResponse(call: Call<PodcastListBase>, response: Response<PodcastListBase>) {
                val podcasts = response.takeIf { it.isSuccessful }?.body()?.podcastList?.podcast
                    ?: emptyList()
                liveOverviewList.value = podcasts
            }
        })
    }

    companion object {
        private const val TAG = "OverviewViewModel"
    }
}