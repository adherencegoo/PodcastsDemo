package xdd.example.podcastsdemo.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import xdd.example.podcastsdemo.databinding.OverviewVhBinding
import xdd.example.podcastsdemo.model.data.Podcast
import xdd.example.podcastsdemo.ui.BindingRecyclerAdapter

class OverviewAdapter(lifecycleOwner: LifecycleOwner,
                      private val viewModel: OverviewViewModel)
    : BindingRecyclerAdapter<Podcast, OverviewAdapter.OverviewVH>(lifecycleOwner) {

    class OverviewVH(private val binding: OverviewVhBinding) : BindingRecyclerAdapter.BindingVH<Podcast>(binding.root) {
        override fun bind(index: Int, data: Podcast) {
            binding.podcast = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OverviewVhBinding.inflate(inflater, parent, false)
        binding.viewModel = viewModel
        lifecycleOwner?.let {
            binding.lifecycleOwner = it
        }
        return OverviewVH(binding)
    }


}