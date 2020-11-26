package xdd.example.podcastsdemo.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import xdd.example.podcastsdemo.databinding.FeedVhBinding
import xdd.example.podcastsdemo.model.data.PodcastContentFeed
import xdd.example.podcastsdemo.ui.BindingRecyclerAdapter

class FeedAdapter(
    lifecycleOwner: LifecycleOwner,
    private val viewModel: DetailsViewModel
)
    : BindingRecyclerAdapter<PodcastContentFeed, FeedAdapter.FeedVH>(lifecycleOwner) {
    class FeedVH(private val binding: FeedVhBinding) : BindingRecyclerAdapter.BindingVH<PodcastContentFeed>(binding.root) {
        override fun bind(index: Int, data: PodcastContentFeed) {
            binding.contentFeed = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FeedVhBinding.inflate(inflater, parent, false)
        binding.viewModel = viewModel
        lifecycleOwner?.let {
            binding.lifecycleOwner = it
        }
        return FeedVH(binding)
    }
}