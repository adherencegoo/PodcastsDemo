package xdd.example.podcastsdemo.ui.playing

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import xdd.example.podcastsdemo.databinding.PlayingFragmentBinding
import xdd.example.podcastsdemo.model.data.PlayingAsset

class PlayingFragment : Fragment() {
    companion object {
        private const val KEY = "PLAYING_ASSET"

        fun newInstance(asset: PlayingAsset) = PlayingFragment().apply {
            arguments = bundleOf(KEY to asset)
        }
    }

    private lateinit var fragmentBinding: PlayingFragmentBinding
    private lateinit var viewModel: PlayingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = PlayingFragmentBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = this

        // TODO: 2020/11/26 reuse toolbar for 3 fragments
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

        viewModel = ViewModelProvider(this).get(PlayingViewModel::class.java).apply {
            setPlayingAsset(requireContext(), arguments?.get(KEY) as PlayingAsset)
        }
        fragmentBinding.viewModel = viewModel
    }
}