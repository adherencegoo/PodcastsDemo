package xdd.example.podcastsdemo.ui.details

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import xdd.example.podcastsdemo.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var fragmentBinding: DetailsFragmentBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        fragmentBinding = DetailsFragmentBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = this
        fragmentBinding.toolbar.setNavigationOnClickListener {
            (it.context as? Activity)?.onBackPressed()
        }
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as? AppCompatActivity)?.let { compatActivity ->
            compatActivity.setSupportActionBar(fragmentBinding.toolbar)
            compatActivity.supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }

        feedAdapter = FeedAdapter(this)

        fragmentBinding.feedRecycler.apply {
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java).apply {
            livePodcastCollection.observe(this@DetailsFragment, Observer {
                fragmentBinding.podcastCollection = it
                feedAdapter.setData(it?.podcastContentFeed ?: emptyList())
            })

            liveErrorMsg.observe(this@DetailsFragment, Observer { msg ->
                Snackbar.make(fragmentBinding.root, msg, Snackbar.LENGTH_LONG)
            })
        }


        viewModel.updateDetails()
    }

}