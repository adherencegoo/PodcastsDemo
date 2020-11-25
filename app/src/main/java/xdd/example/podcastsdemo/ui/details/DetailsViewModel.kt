package xdd.example.podcastsdemo.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xdd.example.podcastsdemo.model.data.PodcastCollection
import xdd.example.podcastsdemo.model.data.PodcastDetailsBase
import xdd.example.podcastsdemo.model.repo.PodcastRepo

class DetailsViewModel : ViewModel() {

    private val mutableErrorMsg = MutableLiveData<String>()
    val liveErrorMsg: LiveData<String>
        get() = mutableErrorMsg

    private val mutablePodcastCollection = MutableLiveData<PodcastCollection>()
    val livePodcastCollection: LiveData<PodcastCollection>
        get() = mutablePodcastCollection

    fun updateDetails() {
        PodcastRepo.service.podcastDetails().enqueue(object : Callback<PodcastDetailsBase> {
            override fun onFailure(call: Call<PodcastDetailsBase>, t: Throwable) {
                mutablePodcastCollection.value = null
                mutableErrorMsg.value = t.localizedMessage
            }

            override fun onResponse(call: Call<PodcastDetailsBase>, response: Response<PodcastDetailsBase>) {
                val podcastCollection = response.takeIf { it.isSuccessful }?.body()?.podcastDetails?.podcastCollection
                mutablePodcastCollection.value = podcastCollection
            }
        })
    }
}