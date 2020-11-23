package xdd.example.podcastsdemo.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import xdd.example.podcastsdemo.databinding.OverviewVhBinding
import xdd.example.podcastsdemo.model.data.Podcast
import xdd.example.podcastsdemo.ui.BindingRecyclerAdapter
import java.lang.ref.WeakReference

class OverviewAdapter(lifecycleOwner: LifecycleOwner) : BindingRecyclerAdapter<Podcast, OverviewAdapter.OverviewVH>() {

    class OverviewVH(private val binding: OverviewVhBinding) : BindingRecyclerAdapter.BindingVH<Podcast>(binding.root) {
        override fun bind(index: Int, data: Podcast) {
            binding.artistName = data.artistName
            binding.podcastName = data.name
        }
    }

    private val weakOwner = WeakReference(lifecycleOwner)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OverviewVhBinding.inflate(inflater, parent, false)
        weakOwner.get()?.let {
            binding.lifecycleOwner = it
        }
        return OverviewVH(binding)
    }


}