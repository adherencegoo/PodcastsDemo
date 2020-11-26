package xdd.example.podcastsdemo.ui.details

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xdd.example.podcastsdemo.R
import xdd.example.podcastsdemo.model.data.PlayingAsset
import xdd.example.podcastsdemo.model.data.PodcastCollection
import xdd.example.podcastsdemo.model.data.PodcastContentFeed
import xdd.example.podcastsdemo.model.data.PodcastDetailsBase
import xdd.example.podcastsdemo.model.repo.PodcastRepo
import xdd.example.podcastsdemo.ui.playing.PlayingFragment

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

    fun onFeedClicked(view: View, collection: PodcastCollection, feed: PodcastContentFeed) {
        val asset = PlayingAsset(collection.artworkUrl600, feed)
        (view.context as? FragmentActivity)
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, PlayingFragment.newInstance(asset))
            ?.addToBackStack(null)
            ?.commit()
    }
}